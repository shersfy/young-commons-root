package org.young.upgrade;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.young.upgrade.utils.ResourceUtil;

public class RunUpgrade {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RunUpgrade.class);
	private static final String default_sql = "classpath:/upgrade.sql";
	
	private UpgradeProperties upgradeProperties;
	
	public RunUpgrade(UpgradeProperties upgradeProperties) {
		super();
		this.upgradeProperties = upgradeProperties;
	}

	public void upgradeDb(DataSource dataSource) {
		if (upgradeProperties==null
				|| dataSource==null
				|| StringUtils.isBlank(upgradeProperties.getVersion())) {
			return;
		}
		LOGGER.info("upgrade database to version {} begining ...", upgradeProperties.getVersion());
		if (upgradeProperties.getUpgradeScript()==null || !upgradeProperties.getUpgradeScript().exists()) {
			try {
				upgradeProperties.setUpgradeScript(ResourceUtil.getResource(default_sql));
			} catch (Exception e) {
				LOGGER.error("", e);
			}
		}
		
		String database = null;
		Connection conn = null;
		ResultSet res1  = null;
		try {
			conn = dataSource.getConnection();
			database = getDatabaseName(conn.getMetaData().getURL());
			res1 = conn.getMetaData().getColumns(database, null, Upgrade.TABLE, "%");
			if (!res1.next()) {
				// 表不存在创建表
				createTable(conn);
			}
			if (existVersion(conn)) {
				LOGGER.info("'{}' is latest version", upgradeProperties.getVersion());
				return;
			}
			// 执行脚本
			executeScript(conn);
			
		} catch (Exception e) {
			LOGGER.error("upgradeDb() error", e);
		} finally {
			if (res1!=null) {
				try {
					res1.close();
				} catch (Exception e2) {
					// ignore
				}
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (Exception e2) {
					// ignore
				}
			}
			LOGGER.info("upgrade database to version {} finished",     upgradeProperties.getVersion());
		}
		
	}
	
	public void upgradeMongo() {
		LOGGER.info("upgrade mongodb to version {} begining ...", upgradeProperties.getVersion());
		LOGGER.info("upgrade mongodb to version {} finished",     upgradeProperties.getVersion());
	}
	
	/**创建表**/
	private void createTable(Connection conn) throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			LOGGER.info(Upgrade.DDL);
			stmt.execute(Upgrade.DDL);
		} finally {
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (Exception e2) {
					// ignore
				}
			}
		}
	}
	
	/**版本存在**/
	private boolean existVersion(Connection conn) throws SQLException {
		
		String sql = "SELECT COUNT(1) FROM %s WHERE app_version='%s'";
		sql = String.format(sql, Upgrade.TABLE, upgradeProperties.getVersion());
		
		Statement stmt = null;
		ResultSet res  = null;
		try {
			stmt = conn.createStatement();
			LOGGER.info(sql);
			res = stmt.executeQuery(sql);
			
			if (!res.next()) {
				return false;
			}
			if (res.getInt(1)>0) {
				return true;
			}
			
			return false;
			
		} finally {
			if (res!=null) {
				try {
					res.close();
				} catch (Exception e2) {
					// ignore
				}
			}
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (Exception e2) {
					// ignore
				}
			}
		}
	}
	
	/**执行SQL脚本文件**/
	private void executeScript(Connection conn) {
		
		LOGGER.info("execute upgrade script {}", upgradeProperties.getUpgradeScript().getFilename());
		
		String insertVersion = "INSERT INTO %s VALUES ('%s', '%s', '%s')";
		insertVersion = String.format(insertVersion, 
				Upgrade.TABLE, 
				upgradeProperties.getApplicationName(),
				upgradeProperties.getVersion(),
				DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
		
		BufferedReader reader = null;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			LOGGER.info(insertVersion);
			stmt.addBatch(insertVersion);

			if (upgradeProperties.getUpgradeScript().exists()) {
				reader = new BufferedReader(new InputStreamReader(upgradeProperties.getUpgradeScript().getInputStream(), 
						"UTF-8"));
				StringBuffer sql = new StringBuffer(0);
				while(reader.ready()){
					String line = reader.readLine();
					// 跳过空行、注释行
					if(StringUtils.isBlank(line) 
							|| line.trim().startsWith("--") 
							|| line.trim().startsWith("#")){
						continue;
					}
					sql.append(line);
					if(line.trim().endsWith(";")){
						LOGGER.info(sql.toString());
						stmt.addBatch(sql.toString());
						sql.setLength(0);
					}
				}
			}
			stmt.executeBatch();
		} catch (Exception ex) {
			LOGGER.error("", ex);
		} finally {
			IOUtils.closeQuietly(reader);
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (Exception e2) {
					// ignore
				}
			}
		}
	}

	public UpgradeProperties getUpgradeProperties() {
		return upgradeProperties;
	}

	public void setUpgradeProperties(UpgradeProperties upgradeProperties) {
		this.upgradeProperties = upgradeProperties;
	}
	
	protected String getDatabaseName(String url) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		
		String[] arr  = url.split("[;|?]");
		String tmp    = arr[0].trim();
		String dbName = tmp.substring(tmp.lastIndexOf("/")+1);
		return StringUtils.isBlank(dbName)?null:dbName;
	}

}
