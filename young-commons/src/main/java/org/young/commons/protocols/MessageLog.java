package org.young.commons.protocols;

public class MessageLog extends BaseProtocol {

    private static final long serialVersionUID = 1L;

    private String phone;

    private String email;

    private String wechat;

    private String type;

    private int smsConfigId;

    private String smsConfigName;

    private String content;

    private String status;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSmsConfigId() {
		return smsConfigId;
	}

	public void setSmsConfigId(int smsConfigId) {
		this.smsConfigId = smsConfigId;
	}

	public String getSmsConfigName() {
		return smsConfigName;
	}

	public void setSmsConfigName(String smsConfigName) {
		this.smsConfigName = smsConfigName;
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
