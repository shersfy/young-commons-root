package org.young.commons.protocols;

public class BatchProcessLog extends BaseProtocol {

	private static final long serialVersionUID = 1L;

	/**
	 * 业务名称
	 */
	private String businessName = "";
	/**
	 * 源表
	 */
	private String sourceTable = "";
	/**
	 * 目标表
	 */
	private String targetTable = "";
	
	/**
	 * 批处理总记录数
	 */
	private long totalCount;
	/**
	 * 批处理大小
	 */
	private long batchSize;
	/**
	 * 已处理记录数
	 */
	private long finishedCount;
	/**
	 * 批处理新增记录数
	 */
	private long insertCount;
	/**
	 * 批处理更新记录数
	 */
	private long updateCount;
	/**
	 * 批处理删除记录数
	 */
	private long deleteCount;
	/**
	 * 批处理错误记录数
	 */
	private long errorCount;

	/**
	 * 错误信息
	 */
	private String errorMessage;
	/**
	 * 源数据
	 */
	private String sourceData = "";
	/**
	 * 目标数据
	 */
	private String targetData = "";
	/**
	 * 错误源数据
	 */
	private String errorSourceData = "";
	/**
	 * 错误目标数据
	 */
	private String errorTargetData = "";
	
	/**
	 * 版本号
	 */
	private String version;

	public BatchProcessLog() {
		super();
	}

	public BatchProcessLog(String businessName, String sourceTable, String targetTable, long batchSize) {
		super();
		this.businessName = businessName;
		this.sourceTable = sourceTable;
		this.targetTable = targetTable;
		this.batchSize = batchSize;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getSourceTable() {
		return sourceTable;
	}

	public void setSourceTable(String sourceTable) {
		this.sourceTable = sourceTable;
	}

	public String getTargetTable() {
		return targetTable;
	}

	public void setTargetTable(String targetTable) {
		this.targetTable = targetTable;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public long getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(long batchSize) {
		this.batchSize = batchSize;
	}

	public long getFinishedCount() {
		return finishedCount;
	}

	public void setFinishedCount(long finishedCount) {
		this.finishedCount = finishedCount;
	}

	public long getInsertCount() {
		return insertCount;
	}

	public void setInsertCount(long insertCount) {
		this.insertCount = insertCount;
	}

	public long getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(long updateCount) {
		this.updateCount = updateCount;
	}

	public long getDeleteCount() {
		return deleteCount;
	}

	public void setDeleteCount(long deleteCount) {
		this.deleteCount = deleteCount;
	}

	public long getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(long errorCount) {
		this.errorCount = errorCount;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSourceData() {
		return sourceData;
	}

	public void setSourceData(String sourceData) {
		this.sourceData = sourceData;
	}

	public String getTargetData() {
		return targetData;
	}

	public void setTargetData(String targetData) {
		this.targetData = targetData;
	}

	public String getErrorSourceData() {
		return errorSourceData;
	}

	public void setErrorSourceData(String errorSourceData) {
		this.errorSourceData = errorSourceData;
	}

	public String getErrorTargetData() {
		return errorTargetData;
	}

	public void setErrorTargetData(String errorTargetData) {
		this.errorTargetData = errorTargetData;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}
