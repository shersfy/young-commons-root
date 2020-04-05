package org.young.commons.protocols;

public class ZuulApiTokenLog extends BaseProtocol {

	private static final long serialVersionUID = 1L;
	
	public ZuulApiTokenLog(String token) {
		super();
		this.token = token;
	}

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
