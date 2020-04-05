package org.young.commons.protocols;

/**
 * datahub-zuul与datahub-admin数据交换
 * @author pengy
 * @date 2018年12月3日
 */
public class ZuulMessage extends BaseProtocol{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String cmd;
	private Object data;
	
	public ZuulMessage() {
		super();
	}
	
	public ZuulMessage(String cmd, String data) {
		super();
		this.cmd = cmd;
		this.data = data;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


}
