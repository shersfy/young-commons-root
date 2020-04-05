package org.young.commons.meta;

import org.young.commons.beans.BaseBean;

/**
 *  访问授权
 * @author Young
 * 2019年7月5日
 */
public class AccessKey extends BaseBean {

	private static final long serialVersionUID = 1L;
	
	private String appId;
	
	private String secretKey;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	
}
