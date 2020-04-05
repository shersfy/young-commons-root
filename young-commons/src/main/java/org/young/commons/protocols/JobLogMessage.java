package org.young.commons.protocols;

import org.slf4j.event.Level;

/**
 * datahub-job-manager任务执行日志
 * @author pengy
 * @date 2018年12月3日
 */
public class JobLogMessage extends BaseProtocol {

	private static final long serialVersionUID = 1L;
	/**任务ID**/
	private Long jobId;
	
	/**任务执行日志ID**/
	private Long logId;
	
	/**任务步骤标识序号**/
	private Integer nodeOrder;
	
	/**日志级别**/
	private Level level;
	
	/**日志内容**/
	private String msg;
	
	public JobLogMessage() {
		this.level = Level.INFO;
	}

	public JobLogMessage(Long jobId, Long logId, Integer nodeOrder, String msg) {
		super();
		this.jobId = jobId;
		this.logId = logId;
		this.nodeOrder = nodeOrder;
		this.msg = msg;
		this.level = Level.INFO;
	}
	
	public JobLogMessage(Long jobId, Long logId, Integer nodeOrder, Level level, String msg) {
		this(jobId, logId, nodeOrder, msg);
		this.level = level;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Integer getNodeOrder() {
		return nodeOrder;
	}

	public void setNodeOrder(Integer nodeOrder) {
		this.nodeOrder = nodeOrder;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
