package org.young.commons.protocols;

/**
 * 主数据错误数据日志
 * @author Young
 * 2019年9月27日
 */
public class MdErrorDataLog extends BaseProtocol {

	private static final long serialVersionUID = 1L;
	/**
	 * 版本号
	 */
	private String version;
	
	/**
	 * 源数据库名
	 */
	private String srcDatabase;
	
	/**
	 * 源表名
	 */
	private String srcTable;
	
	/**
	 * 目标数据库名
	 */
	private String tarDatabase;
	
	/**
	 * 目标表名
	 */
	private String tarTable;
	
	/**
	 * 关键字
	 */
	private String keyword;
	
	/**
	 * 错误提示信息
	 */
	private String msg;
	
	/**
	 * 数据记录
	 */
	private String record;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSrcDatabase() {
		return srcDatabase;
	}

	public void setSrcDatabase(String srcDatabase) {
		this.srcDatabase = srcDatabase;
	}

	public String getSrcTable() {
		return srcTable;
	}

	public void setSrcTable(String srcTable) {
		this.srcTable = srcTable;
	}

	public String getTarDatabase() {
		return tarDatabase;
	}

	public void setTarDatabase(String tarDatabase) {
		this.tarDatabase = tarDatabase;
	}

	public String getTarTable() {
		return tarTable;
	}

	public void setTarTable(String tarTable) {
		this.tarTable = tarTable;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

}