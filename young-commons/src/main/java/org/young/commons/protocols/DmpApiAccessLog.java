package org.young.commons.protocols;

/**
 * 项目操作日志
 * @author Young
 * 2019年9月27日
 */
public class DmpApiAccessLog extends BaseProtocol {

	private static final long serialVersionUID = 1L;

	/**
	 * 唯一标识
	 */
	private String uuid;
	
	/**
	 * 用户ID
	 */
	private String userId;
	
	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 业务域
	 */
	private String businessScope;

	/**
	 * 项目名称
	 */
	private String projectName;


	/**
	 * 业务域id
	 */
	private String businessScopeId;

	/**
	 * 项目id
	 */
	private String projectId;

	/**
	 * 服务id
	 */
	private String apiId;

	/**
	 * 访问授权appId
	 */
	private String appId;
	/**
	 * API名称
	 */
	private String apiName;
	/**
	 * API访问路径
	 */
	private String apiUrl;

	/**
	 * 执行状态
	 */
	private String status;

	/**
	 * 请求源IP
	 */
	private String requestIp;

	/**
	 * 请求位置
	 */
	private String requestLocation;

	/**
	 * 请求时间
	 */
	private long requestTime;

	/**
	 * 请求参数
	 */
	private String requestParameters;

	/**
	 * 参数大小
	 */
	private String requestParametersSize;

	/**
	 * Content-Type
	 */
	private String contentType;

	/**
	 * 响应时间
	 */
	private long responseTime;

	/**
	 * 响应数据
	 */
	private String responseData;

	/**
	 * 响应数据
	 */
	private String responseDataSize;

	/**
	 * 消耗时间
	 */
	private long spendTime;
	
	/**
	 * 访问源系统
	 */
	private String system;
	
	/**
	 * 任务类型
	 */
	private String taskType;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getBusinessScopeId() {
		return businessScopeId;
	}

	public void setBusinessScopeId(String businessScopeId) {
		this.businessScopeId = businessScopeId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRequestIp() {
		return requestIp;
	}

	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}

	public String getRequestLocation() {
		return requestLocation;
	}

	public void setRequestLocation(String requestLocation) {
		this.requestLocation = requestLocation;
	}

	public long getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(long requestTime) {
		this.requestTime = requestTime;
	}

	public String getRequestParameters() {
		return requestParameters;
	}

	public void setRequestParameters(String requestParameters) {
		this.requestParameters = requestParameters;
	}

	public String getRequestParametersSize() {
		return requestParametersSize;
	}

	public void setRequestParametersSize(String requestParametersSize) {
		this.requestParametersSize = requestParametersSize;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}

	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

	public String getResponseDataSize() {
		return responseDataSize;
	}

	public void setResponseDataSize(String responseDataSize) {
		this.responseDataSize = responseDataSize;
	}

	public long getSpendTime() {
		return spendTime;
	}

	public void setSpendTime(long spendTime) {
		this.spendTime = spendTime;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

}