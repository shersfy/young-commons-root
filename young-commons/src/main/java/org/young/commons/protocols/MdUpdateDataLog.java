package org.young.commons.protocols;

/**
 * 主数据-增量日志
 * @author Young
 * 2019年9月27日
 */
public class MdUpdateDataLog extends BaseProtocol {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 唯一标识ID
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
	 * 增量类型(上传/监听)
	 */
	private String updateType;
	
	/**
	 * 状态(未处理；已处理)
	 */
	private String status;
	
	/**
	 * 来源系统
	 */
	private int provider;
	
	/**
	 * 加密类型
	 */
	private String encryptType;
	
	/**
	 * 数据类型
	 */
	private String dataType;
	
	/**
	 * 关键字
	 */
	private String keyword;
	
	/**
	 * 数据内容
	 */
	private String data;
	
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

	public String getUpdateType() {
		return updateType;
	}

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getProvider() {
		return provider;
	}

	public void setProvider(int provider) {
		this.provider = provider;
	}

	public String getEncryptType() {
		return encryptType;
	}

	public void setEncryptType(String encryptType) {
		this.encryptType = encryptType;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public long getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(long updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

		
}