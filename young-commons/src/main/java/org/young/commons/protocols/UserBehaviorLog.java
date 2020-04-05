package org.young.commons.protocols;

/**
 * datahub-admin用户行为日志
 * @author pengy
 * @date 2018年12月3日
 */
public class UserBehaviorLog extends BaseProtocol {

	private static final long serialVersionUID = 1L;
	
	
	/*
	 * 访问URL
	 */
	private String url;
	
	/**
	 * 接口请求参数
	 */
	private String params;
	
	/**
	 * 请求类型
	 */
	private String contentType;
	
	/**
	 * IP地址
	 */
	private String ipAddress;
	
	/**
	 * 数据来源
	 */
	private String datasourceFrom;
	
	/**
	 * 请求方法
	 */
	private String requestMethod;
	
	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 用户名称
	 */
	private String userName;
	
	/**
	 * 接口描述
	 */
	private String interfaceDesc;
	
	public UserBehaviorLog() {
		super();
	}
	
	public UserBehaviorLog(String url, String params, String contentType, String ipAddress,
			String datasourceFrom, String requestMethod, Long tenantId, String tenantName, String userId,
			String userName, String interfaceDesc) {
		super();
		this.url = url;
		this.params = params;
		this.contentType = contentType;
		this.ipAddress = ipAddress;
		this.datasourceFrom = datasourceFrom;
		this.requestMethod = requestMethod;
		this.setTenantId(tenantId);
		this.setTenantName(tenantName);
		this.userId = userId;
		this.userName = userName;
		this.interfaceDesc = interfaceDesc;
	}



	public String getInterfaceDesc() {
		return interfaceDesc;
	}

	public void setInterfaceDesc(String interfaceDesc) {
		this.interfaceDesc = interfaceDesc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getDatasourceFrom() {
		return datasourceFrom;
	}

	public void setDatasourceFrom(String datasourceFrom) {
		this.datasourceFrom = datasourceFrom;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
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
	
}
