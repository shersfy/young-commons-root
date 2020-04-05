package org.young.commons.protocols;

public class WeixinSyncErrorLog extends BaseProtocol {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 唯一标识
	 */
	private String uuid;
	
	/**
	 * 任务ID
	 */
	private String jobId;
	
	/**
	 * 任务类型
	 */
	private String jobType;
	
	/**
	 * 记录ID
	 */
	private String recordId;
	
	/**
	 * 执行操作
	 */
	private String recordAction;
	
	/**
	 * 错误编码
	 */
	private String errcode;
	
	/**
	 * 错误信息
	 */
	private String errmsg;
	
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

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getRecordAction() {
		return recordAction;
	}

	public void setRecordAction(String recordAction) {
		this.recordAction = recordAction;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public long getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(long updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

}
