package org.young.commons.protocols;

/**
 * datahub-finance财务FBI日志
 * @author pengy
 * @date 2018年12月3日
 */
public class FinanceFbiDataLog extends BaseProtocol{

	private static final long serialVersionUID = 1L;

	/**
	 * 日志类型
	 */
	private String typeName;
	/**
	 * 日志类型编码
	 */
	private String typeCode;

	/**
	 * 日志内容
	 */
	private String content;
	/**
	 *  请求URL
	 */
	private String url;
	/**
	 * 请求ip地址
	 */
	private String ipAddr;
	/**
	 * 目标地址
	 */
	private String destUrl;
	
	/**
	 * 返回结果
	 */
	private String result;

	public String getDestUrl() {
		return destUrl;
	}

	public void setDestUrl(String destUrl) {
		this.destUrl = destUrl;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public FinanceFbiDataLog() {
		super();
	}

	public FinanceFbiDataLog(String content, String url, String ipAddr) {
		super();
		this.content = content;
		this.url = url;
		this.ipAddr = ipAddr;
	}

	public FinanceFbiDataLog(String typeName, String typeCode, String content, String url, String ipAddr) {
		super();
		this.typeName = typeName;
		this.typeCode = typeCode;
		this.content = content;
		this.url = url;
		this.ipAddr = ipAddr;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
}

