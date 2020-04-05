package org.young.commons.protocols;

/**
 * DataX运行日志
 * @author Young
 * 2019年9月27日
 */
public class DmpDataxLog extends BaseProtocol {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 任务ID
	 */
	private long taskId;
	
	/**
	 * 唯一标识
	 */
	private String uuid;
	
	/**
     * 名称
     */
    private String name;

    /**
     * json配置文件
     */
    private String jsonConfig;

    /**
     * 是否定时调度: 是, 否
     */
    private String isQuartz;

    /**
     * 定时调度任务
     */
    private String quartzConfig;
    
    /**
     * 日志信息
     */
    private String content;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJsonConfig() {
		return jsonConfig;
	}

	public void setJsonConfig(String jsonConfig) {
		this.jsonConfig = jsonConfig;
	}

	public String getIsQuartz() {
		return isQuartz;
	}

	public void setIsQuartz(String isQuartz) {
		this.isQuartz = isQuartz;
	}

	public String getQuartzConfig() {
		return quartzConfig;
	}

	public void setQuartzConfig(String quartzConfig) {
		this.quartzConfig = quartzConfig;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}