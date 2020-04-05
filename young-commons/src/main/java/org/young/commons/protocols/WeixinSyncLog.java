package org.young.commons.protocols;

public class WeixinSyncLog extends BatchProcessLog {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 唯一标识
	 */
	private String uuid;
	
	/**
	 * 来源系统
	 */
	private int sourceSystem;
	
	/**
	 * 查看结果
	 */
	private String resultLink;
	
	/**
	 * 执行状态
	 */
	private String status;
	
	/**
	 * 错误统计状态：已统计/未统计
	 */
	private String errorStatus;
	
	/**
	 * 更新时间
	 */
	private long updateTimestamp;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(int sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public String getResultLink() {
		return resultLink;
	}

	public void setResultLink(String resultLink) {
		this.resultLink = resultLink;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}

	public long getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(long updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

}
