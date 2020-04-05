package org.young.commons.protocols;

import org.apache.commons.lang.StringUtils;
import org.young.commons.beans.BaseBean;
import org.young.commons.utils.CamelCaseUtils;

public class BaseProtocol extends BaseBean {

	private static final long serialVersionUID = 1L;
	
	public static final String INDEX_ID = "id";
	public static final String INDEX_TIMESTAMP = "timestamp";
	
	private String id;
	
	/**
	 * 租户id
	 */
	private Long tenantId;
	
	/**
	 * 租户名称
	 */
	private String tenantName;
	
	private long timestamp;
	
	private String className;
	/**
	 * 指定更新字段(不指定时插入)
	 */
	private String updateKey;
	
	public BaseProtocol() {
		super();
		this.timestamp = System.currentTimeMillis();
		this.className = getClass().getName();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getUpdateKey() {
		return updateKey;
	}

	public void setUpdateKey(String updateKey) {
		this.updateKey = updateKey;
	}
	
	public String getQueueName(String appname) {
		
		String queue = this.getClass().getSimpleName();
		queue = CamelCaseUtils.toUnderlineString(queue);
		queue = queue.replace("_", ".");
		
		if (StringUtils.isBlank(appname)) {
			return queue;
		}
		
		appname = appname.replaceAll("_|-", ".");
		queue = String.format("%s.%s", appname, queue);
		
		return queue;
	}

}
