package org.young.commons.protocols;

/**
 * 微服务启动日志
 * @author pengyang
 */
public class ServerStartLog extends BaseProtocol {

	private static final long serialVersionUID = 1L;
	/**
	 * 服务名称
	 */
	private String serviceId;
	
	/**
	 * 服务实例ID
	 */
	private String instanceId;
	
	/**
	 * 日志服务器
	 */
	private String logServer;
	
	/**
	 * 提示信息
	 */
	private String msg;

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getLogServer() {
		return logServer;
	}

	public void setLogServer(String logServer) {
		this.logServer = logServer;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
