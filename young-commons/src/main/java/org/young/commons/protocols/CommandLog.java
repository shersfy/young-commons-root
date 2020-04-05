package org.young.commons.protocols;

/**
 * BUI构建命令日志
 * @author pengyang
 *
 */
public class CommandLog extends BaseProtocol {

	private static final long serialVersionUID = 1L;
	/**
	 * 业务ID
	 */
	private String serviceId;
	/**
	 * 命令短语
	 */
	private String cmd;
	/**
	 * 日志内容
	 */
	private String content = "";
	/**
	 * 状态
	 */
	private String level = "INFO";
	
	public CommandLog() {
		super();
	}
	
	public CommandLog(String cmd) {
		this();
		this.cmd = cmd;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}
