package org.young.commons.protocols;

public class GatewayG2Log extends RouteLog {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 访问授权应用ID
	 */
	private String appId;

	/**
	 * 路由配置信息
	 */
	private String routeConfig;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getRouteConfig() {
		return routeConfig;
	}

	public void setRouteConfig(String routeConfig) {
		this.routeConfig = routeConfig;
	}
	
}
