package org.young.commons.connector.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.young.commons.constant.Const;
import org.young.commons.exception.DatahubException;
import org.young.commons.exception.TooManyConnectionException;
import org.young.commons.meta.ColumnMeta;
import org.young.commons.meta.DBAccessType;
import org.young.commons.meta.DBMeta;
import org.young.commons.meta.TableMeta;
import org.young.commons.meta.TableType;
import org.young.commons.utils.FunUtil;

public class PostgreSQLConnector extends DbConnectorInterface {

	@Override
	public String loadDriverClass(DBAccessType type) {

		String driver = "org.postgresql.Driver";
		return driver;
	}

	@Override
	public String queryByPage(String baseSql, long pageNo, long pageSize)
	    throws DatahubException {
		if (StringUtils.isBlank(baseSql)) {
			return null;
		}
		if (pageNo < 1 || pageSize < 0) {
			return baseSql;
		}

		long start = (pageNo - 1) * pageSize;
		String aliasA = "T" + System.currentTimeMillis();

		StringBuffer sql = new StringBuffer(0);
		// baseSql未分页
		if(!StringUtils.containsIgnoreCase(baseSql, " LIMIT ")){
			sql.append(baseSql);
			sql.append(" LIMIT ");
			sql.append(pageSize);
			sql.append(" OFFSET ");
			sql.append(start);
			return sql.toString();
		}
		// baseSql在已经分页的基础上二次分页
		sql.append("SELECT * FROM (");
		sql.append(baseSql);
		sql.append(") ");
		sql.append(aliasA);
		sql.append(" LIMIT ");
		sql.append(pageSize);
		sql.append(" OFFSET ");
		sql.append(start);

		return sql.toString();
	}

	@Override
	public List<DBMeta> getDatabases(Connection conn) throws DatahubException {
		List<DBMeta> dbs = new ArrayList<DBMeta>();
		ResultSet rs = null;
		try {
			if(conn == null){
				throw new SQLException("connection is null");
			}
			rs = conn.getMetaData().getSchemas();
			while(rs.next()){
				DBMeta meta = new DBMeta();
				BeanUtils.copyProperties(meta, getDbMeta());
				meta.setName(rs.getString("TABLE_SCHEM"));
				meta.setSchema(rs.getString("TABLE_SCHEM"));
				meta.setName(meta.getName());
				if("information_schema".equals(meta.getName())
						|| "pg_catalog".equals(meta.getName())
						|| "pg_toast_temp_1".equals(meta.getName())){
					continue;
				}
				//initPublicInfo(meta);
				dbs.add(meta);
			}

		} catch (Exception ex) {
		    throw DatahubException.throwDatahubException("list all database error", ex);
		} finally {
			close(rs);
		}
		return dbs;
	}

	@Override
	public void setDbMeta(DBMeta dbMeta) {
		
		dbMeta.setSchema(dbMeta.getName());
		if(StringUtils.isBlank(dbMeta.getUrl())){
			dbMeta.setUrl(String.format("jdbc:postgresql://%s:%s/%s",
					dbMeta.getHost(), dbMeta.getPort(), dbMeta.getName()));
		}
		this.dbMeta = dbMeta;
	}

	@Override
	public List<TableMeta> getTables(String catalog, String schema, TableType[] types, 
			Connection conn) throws DatahubException {
		if(StringUtils.isNotBlank(catalog)){
			getDbMeta().setName(catalog);
		}
		return super.getTables(catalog, schema, types, conn);
	}

	/** java.sql.Types映射为数据库类型 **/
	private static Map<Integer, String> javaTypeToDbTypeMap = new HashMap<Integer, String>();
	static {
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.ARRAY), "bytea");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.BIGINT), "bigint");//oid,int8
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.BINARY), "bytea");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.BIT), "bool");//bit
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.BLOB), "bytea");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.BOOLEAN), "bool");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.CHAR), "char"); //bpchar(x)
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.CLOB), "bytea");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.DATALINK), "text");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.DATE), "date");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.DECIMAL), "decimal");//decimal(x,y)
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.DISTINCT), "text");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.DOUBLE), "float8");//money, float(x)
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.FLOAT), "float"); //float8
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.INTEGER), "int"); //int4,integer
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.JAVA_OBJECT), "bytea");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.LONGNVARCHAR), "text");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.LONGVARBINARY), "bytea");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.LONGVARCHAR), "text");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.NCHAR), "char");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.NCLOB), "bytea");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.NULL), "bytea");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.NUMERIC), "numeric"); //numeric(x,y)
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.NVARCHAR), "varchar");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.OTHER), "bytea");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.REAL), "float4");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.REF), "bytea");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.REF_CURSOR), "bytea");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.ROWID), "oid");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.SMALLINT), "smallint");//int2
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.SQLXML), "text");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.STRUCT), "text");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.TIME), "time"); //timetz
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.TIME_WITH_TIMEZONE), "timetz");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.TIMESTAMP), "timestamp"); //timestamptz
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.TIMESTAMP_WITH_TIMEZONE), "timestamptz");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.TINYINT), "int");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.VARBINARY), "bytea");
		javaTypeToDbTypeMap.put(Integer.valueOf(Types.VARCHAR), "varchar"); //varchar(x), text,name
	}

	@Override
	public String javaTypeToDbType(int javaType, ColumnMeta column)
			throws DatahubException {
		String type = javaTypeToDbTypeMap.get(Integer.valueOf(javaType));
		if( StringUtils.isBlank(type)){
			type = javaTypeToDbTypeMap.get(Integer.valueOf(Types.VARCHAR));
			javaType = Types.VARCHAR;
		}
		
		switch (javaType) {
		case Types.DECIMAL:
		case Types.NUMERIC:
			if(column.getDecimalDigits()>0 && column.getDecimalDigits() <= column.getColumnSize()){
				type = type + String.format("(%s, %s)", column.getColumnSize(), column.getDecimalDigits());
			}
			else if(column.getColumnSize()>0){
				type = type + String.format("(%s, 0)", column.getColumnSize());
			}
			break;
		case Types.CHAR:
		case Types.NCHAR:
			type = type + String.format("(%s)", column.getColumnSize());
			break;
		case Types.VARCHAR:
		case Types.NVARCHAR:
			type = type + String.format("(%s)", column.getColumnSize());
			if(column.getColumnSize() > 255){
				type = javaTypeToDbTypeMap.get(Integer.valueOf(Types.LONGVARCHAR));
			}
			break;
		default:
			break;
		}
		
		return type;

	}
	
	@Override
	public String showCreateTable(TableMeta table, Connection conn) throws DatahubException {
		if(table == null || conn == null){
			return "";
		}
		table.setCatalog(StringUtils.isBlank(table.getCatalog())?null:table.getCatalog());
		table.setSchema(StringUtils.isBlank(table.getSchema())?null:table.getSchema());
		
		List<ColumnMeta> cols = getColumns(table, conn);
		if(cols.isEmpty()){
			return StringUtils.EMPTY;
		}
		
		return getDDLByTable(table, cols, conn);
		
	}

	@Override
	public String getCutPointSql(TableMeta table, ColumnMeta partColumn, 
			String where, long totalSize, int blockCnt) {
		
		StringBuffer partSql = new StringBuffer(0);
		if(table == null
				|| partColumn == null 
				|| blockCnt<2
				|| totalSize<1){
			return partSql.toString();
		}
		
		long pageSize = (long)(Math.ceil((double)totalSize/blockCnt));
		String TA = "TA"+System.currentTimeMillis();
		String union = "UNION\n";
		
		String fullName = this.getFullTableName(table);
		String colName  = this.quotObject(partColumn.getName());
		
		partSql.append("SELECT ");
		partSql.append("MIN(").append(colName).append(") miv, ");
		partSql.append("MAX(").append(colName).append(") mav ");
		partSql.append("FROM (");
		
		// base sql 必须为未分页过的SQL
		partSql.append("SELECT ").append(colName);
		partSql.append(" FROM ").append(fullName).append(" ");
		partSql.append(where==null?"":where.trim());
		partSql.append(" ORDER BY ").append(colName).append(" LIMIT {0} OFFSET {1} )").append(TA);
		
		StringBuffer unionSql = new StringBuffer(0);
		for(int ponit =2; ponit<=blockCnt;  ponit+=2){
			long start = (ponit-1) * pageSize;
			unionSql.append(FunUtil.formatArgs(partSql.toString(), pageSize, start));
			unionSql.append(Const.LINE_SEP);
			unionSql.append(union);
		}
		String sql = unionSql.toString();
		if(sql.endsWith(union)){
			sql = sql.substring(0, sql.length()-union.length());
		}
		if(sql.length()>0){
			sql = sql + "ORDER BY miv";
		}
		
		return sql;
	}

	@Override
	public Throwable connectionException(Throwable ex) {
		
		String err = ex.getMessage();
		if(StringUtils.containsIgnoreCase(err, "too many clients already")){
			return new TooManyConnectionException(ex);
		}
		
		return ex;
	}

	@Override
	public Object getResultObject(ColumnMeta col, ResultSet result) throws SQLException {
		Object obj = null;
		switch (col.getDataType()) {
		case Types.DOUBLE:
			if("money".equalsIgnoreCase(col.getTypeName())){
				String str = result.getString(col.getOrdinalPosition());
				if(str.length()>0){
					try {
						obj = new DecimalFormat("###,###").parseObject(str.substring(1));
					} catch (ParseException e) {
						obj = str;
					}
				} else{
					obj = str;
				}
			} else {
				obj = result.getObject(col.getOrdinalPosition());
			}
			break;
		case Types.DECIMAL:
		case Types.NUMERIC:
			obj = result.getDouble(col.getOrdinalPosition());
			break;
		case Types.OTHER:
			if("line".equalsIgnoreCase(col.getTypeName())
					|| "interval".equalsIgnoreCase(col.getTypeName())){
				obj = result.getString(col.getOrdinalPosition());
			} else {
				obj = result.getObject(col.getOrdinalPosition());
			}
			break;
		default:
			obj = super.getResultObject(col, result);
			break;
		}
		
		return obj;
	}

	@Override
	public void prepareStatementByCursor(PreparedStatement pstmt) throws SQLException {
		if(pstmt==null){
			return;
		}
		pstmt.getConnection().setAutoCommit(false);
		pstmt.setFetchSize(1);
	}
	
	@Override
	public boolean checkAvailable() throws DatahubException {
	    checkUrl("jdbc:postgresql");
		return true;
	}
	
}
