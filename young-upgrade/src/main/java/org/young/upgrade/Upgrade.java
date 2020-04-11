package org.young.upgrade;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * 版本升级数据模型
 * @author pengy
 * @date 2018年11月23日
 */
public class Upgrade implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String TABLE  = "upgrade";
	public static final String COLUMN = "app_name, app_version, upgrade_time";
	public static final String DDL    = "CREATE TABLE upgrade ( "
			+ "app_name varchar(255) NOT NULL, "
			+ "app_version varchar(255) NOT NULL PRIMARY KEY, "
			+ "upgrade_time varchar(255) NOT NULL)";
	
	private String appName;
	
	private String appVersion;
	
	private String upgradeTime;

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getUpgradeTime() {
		return upgradeTime;
	}

	public void setUpgradeTime(String upgradeTime) {
		this.upgradeTime = upgradeTime;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
}
