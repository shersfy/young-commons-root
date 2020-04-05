package org.young.commons.protocols;

/**
 * 主数据-增量日志
 * @author Young
 * 2019年9月27日
 */
public class MdPublishDataLog extends BaseProtocol {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主题
	 */
	private String topic;
	
	/**
	 * 消息ID
	 */
	private String messageId;
	
	/**
	 * 数据内容
	 */
	private String data;
	
	public MdPublishDataLog() {
		super();
	}

	public MdPublishDataLog(String topic, String messageId, String data) {
		super();
		this.topic = topic;
		this.messageId = messageId;
		this.data = data;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	
	
}