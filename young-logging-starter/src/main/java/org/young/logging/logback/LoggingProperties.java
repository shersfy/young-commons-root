package org.young.logging.logback;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.logging.LogLevel;

@ConfigurationProperties(LoggingProperties.PREFIX)
public class LoggingProperties {
	
	public static final String PREFIX = "logging.young.filter";
	
	/**
	 * 日志过滤类型, 默认'default'
	 */
	private FilterType type = FilterType.Default;
	
	/**
	 * 过滤级别，默认error
	 */
	private LogLevel level = LogLevel.ERROR;
	
	/**
	 * 默认租户ID
	 */
	private Long tenantId = 1L;

    /**
     * 租户名称
     */
    private String tenantName = "young";
    
    /**
     * 资源池
     */
    private String resourcePool = "young-pool";
    
    /**
     * 发送错误日志队列, 默认'young.log.direct.error.log'
     */
    private String sendLogQueue = "young.log.direct.error.log";

	public String getSendLogQueue() {
		return sendLogQueue;
	}

	public void setSendLogQueue(String sendLogQueue) {
		this.sendLogQueue = sendLogQueue;
	}

	public FilterType getType() {
		return type;
	}

	public void setType(FilterType type) {
		this.type = type;
	}

	public LogLevel getLevel() {
		return level;
	}

	public void setLevel(LogLevel level) {
		this.level = level;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getResourcePool() {
		return resourcePool;
	}

	public void setResourcePool(String resourcePool) {
		this.resourcePool = resourcePool;
	}

}
