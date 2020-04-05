package org.young.commons.protocols;

/**
 * 迁移任务日志
 * @author Young
 * 2019年8月23日
 */
public class JobsMoveLog extends BaseProtocol{

	private static final long serialVersionUID = 1L;

	/**ID**/
	private String uuid;

	/**项目ID**/
	private Long projectId;
	
	/**项目名称**/
	private String projectName;
	
	/**任务名称**/
	private String jobName;

	/**操作人id**/
	private Long operateId;

	/**操作人名称**/
	private String operateName;

	/**同步状态**/
	private String status;

	/**结束时间**/
	private Long endTimestamp;

	/**日志内容**/
	private String errorMessage;

	/**目录结构**/
	private String treeData = "{}";

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(Long endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getTreeData() {
		return treeData;
	}

	public void setTreeData(String treeData) {
		this.treeData = treeData;
	}

	public Long getOperateId() {
		return operateId;
	}

	public void setOperateId(Long operateId) {
		this.operateId = operateId;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
}
