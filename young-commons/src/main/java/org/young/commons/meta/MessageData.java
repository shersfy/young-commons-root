package org.young.commons.meta;

import com.alibaba.fastjson.JSON;

/**
 * 消息推送数据
 */
public class MessageData {

	private int code;
	private String data;
	
	public MessageData(String data) {
        super();
        this.data = data;
    }
	
    public MessageData(int code, String data) {
        this(data);
        this.data = data;
    }
    
    public MessageData(LogMeta log) {
        super();
        this.data = log.getLine();
    }
    public MessageData(int code, LogMeta log) {
        this(log);
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
	
	
}
