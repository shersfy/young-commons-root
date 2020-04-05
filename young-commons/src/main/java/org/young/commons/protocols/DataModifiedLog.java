package org.young.commons.protocols;

public class DataModifiedLog extends BaseProtocol {

	private static final long serialVersionUID = 1L;
	/**
	 * 业务名称
	 */
	private String businessName = "";
	
	/**
	 * 表名称
	 */
	private String tableName = "";
	
	/**
	 * 操作(新建或更新)
	 */
	private String operate = "";
	
	/**
	 * 数据主键
	 */
	private String pk = "";
	/**
	 * 数据关键字
	 */
	private String keyword = "";
	
	/**
	 * 数据
	 */
	private String data = "";
	
	/**
	 * 通过URL修改
	 */
	private String modifiedByUrl = "";
	
	/**
	 * 修改用户ID
	 */
	private String modifiedUserId = "";
	
	/**
	 * 修改用户名称
	 */
	private String modifiedUserName = "";
	
	public DataModifiedLog() {
		super();
	}

	public DataModifiedLog(String businessName, String tableName, String operate, String pk, String data) {
		super();
		this.businessName = businessName;
		this.tableName = tableName;
		this.operate = operate;
		this.pk = pk;
		this.data = data;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getModifiedByUrl() {
		return modifiedByUrl;
	}

	public void setModifiedByUrl(String modifiedByUrl) {
		this.modifiedByUrl = modifiedByUrl;
	}

	public String getModifiedUserId() {
		return modifiedUserId;
	}

	public void setModifiedUserId(String modifiedUserId) {
		this.modifiedUserId = modifiedUserId;
	}

	public String getModifiedUserName() {
		return modifiedUserName;
	}

	public void setModifiedUserName(String modifiedUserName) {
		this.modifiedUserName = modifiedUserName;
	}
}
