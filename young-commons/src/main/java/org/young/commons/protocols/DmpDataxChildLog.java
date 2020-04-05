package org.young.commons.protocols;

public class DmpDataxChildLog extends BaseProtocol {

	private static final long serialVersionUID = 1L;

	/**
     * 任务ID
     */
    private long taskId;

    /**
     * 日志内容
     */
    private String content;

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
   
}
