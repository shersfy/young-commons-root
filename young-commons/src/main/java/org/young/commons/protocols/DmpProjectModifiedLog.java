package org.young.commons.protocols;

/**
 * 项目操作日志
 * @author Young
 * 2019年9月27日
 */
public class DmpProjectModifiedLog extends BaseProtocol {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 唯一标识
	 */
	private String uuid;
	
	/**
	 * 业务域名称
	 */
	private String businessScope;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * 操作人
	 */
	private String operator;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 执行状态
	 */
	private String status;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}