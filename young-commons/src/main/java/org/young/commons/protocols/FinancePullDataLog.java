package org.young.commons.protocols;
/**
 * atahub-finance财务拉取数据日志
 * @author pengy
 * @date 2018年12月3日
 */
public class FinancePullDataLog extends BaseProtocol{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 日志内容
	 */
	private String content;
	/**
	 * 总条数
	 */
	private int totalNum;
	/**
	 * 系统名称
	 */
	private String sysName;
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 花费时间
	 */
	private int totalTime;

	/**
	 * 拉取数据方式（自动，手动）
	 */
	private String pullType;

	public FinancePullDataLog() {
		super();
	}

	public FinancePullDataLog(String content, int totalNum, String sysName,
			String tableName, int totalTime, String pullType) {
		super();
		this.content = content;
		this.totalNum = totalNum;
		this.sysName = sysName;
		this.tableName = tableName;
		this.totalTime = totalTime;
		this.pullType = pullType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	public String getPullType() {
		return pullType;
	}

	public void setPullType(String pullType) {
		this.pullType = pullType;
	}

}
