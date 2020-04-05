package org.young.commons.protocols;

/**
 * 登录日志
 * @author pengy
 * @date 2018年12月3日
 */
public class LoginLog extends BaseProtocol {

	private static final long serialVersionUID = 1L;
	/**用户ID**/
	private Long userId;
	
	/**用户名**/
	private String username;
	
	/**客户端IP地址**/
	private String remoteAddr;
	
	/**客户端请求源城市**/
	private String remoteCity;
	
	/**客户端Host**/
	private String remoteHost;
	
	/**登录日志ID**/
	private Long loginId;
	
	/**登录用户语言**/
	private String loginLang;
	
	/**登录网址**/
	private String loginUrl;
	
	public LoginLog() {}
	
	public LoginLog(Long userId, String username, Long tenantId, String tenantName, String remoteAddr,
			String remoteHost, Long loginId, String loginLang, String loginUrl) {
		super();
		this.userId = userId;
		this.username = username;
		this.setTenantId(tenantId);
		this.setTenantName(tenantName);
		this.remoteAddr = remoteAddr;
		this.remoteHost = remoteHost;
		this.loginId = loginId;
		this.loginLang = loginLang;
		this.loginUrl = loginUrl;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	public String getLoginLang() {
		return loginLang;
	}

	public void setLoginLang(String loginLang) {
		this.loginLang = loginLang;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getRemoteCity() {
		return remoteCity;
	}

	public void setRemoteCity(String remoteCity) {
		this.remoteCity = remoteCity;
	}

}
