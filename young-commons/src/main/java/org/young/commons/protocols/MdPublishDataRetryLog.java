package org.young.commons.protocols;

/**
 * 主数据-发布数据重试日志
 * @author Young
 * 2019年9月27日
 */
public class MdPublishDataRetryLog extends BaseProtocol {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 唯一标识
	 */
	private String uuid;
	
	/**
	 * 版本号
	 */
	private long version;
	
	/**
	 * 重试次数
	 */
	private int retry;

	/**
	 * 状态(成功；失败；等待)
	 */
	private String status;
	
	/**
	 * 请求URL
	 */
	private String url;
	
	/**
	 * 请求header
	 */
	private String header;
	
	/**
	 * 参数
	 */
	private String params;
	
	/**
	 * 提示信息
	 */
	private String msg;
	
	/**
	 * 更新时间
	 */
	private long updateTimestamp;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public int getRetry() {
		return retry;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(long updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}
	
	
}