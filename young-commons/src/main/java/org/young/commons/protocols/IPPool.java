package org.young.commons.protocols;

public class IPPool extends BaseProtocol{

	private static final long serialVersionUID = 1L;
	
	private String ip = "";
	
	private String adcode = "";
	
	private String province = "";
	
	private String city = "";
	
	private String infocode = "";
	
	public IPPool() {
		super();
	}

	public IPPool(String ip) {
		super();
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAdcode() {
		return adcode;
	}

	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getInfocode() {
		return infocode;
	}

	public void setInfocode(String infocode) {
		this.infocode = infocode;
	}
	
}
