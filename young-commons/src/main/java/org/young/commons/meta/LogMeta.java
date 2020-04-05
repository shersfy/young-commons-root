package org.young.commons.meta;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.event.Level;
import org.young.commons.constant.Const;
import org.young.commons.utils.DateUtil;

/***
 * 行日志结构
 */
public class LogMeta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**日志级别**/
	private Level level;
	/**日志时间yyyy/MM/dd HH:mm:ss**/
	private Date time;
	/**日志内容**/
	private String msg;
	
	public LogMeta() {
		super();
		this.level = Level.DEBUG;
		this.time = new Date();
		this.msg = StringUtils.EMPTY;
	}
	
	public LogMeta(Level level, String msg) {
		super();
		this.level = level==null?Level.DEBUG:level;
		this.msg = msg;
		this.time = new Date();
	}

	public LogMeta(Level level, Date time, String msg) {
		super();
		this.level = level==null?Level.DEBUG:level;
		this.time = time;
		this.msg = msg;
	}

	public String getLine() {
		return getLine(null);
	}
	
	public String getLine(String format) {
		format = StringUtils.isBlank(format)?Const.yyyyMMddHHmmss:format;
		/**日志行**/
		String line = "[%s] %s\t%s\n";
		line = String.format(line, level.name(), DateUtil.format(time, format), msg);
		return line;
	}
	
	public static String logtimeString(){
		return DateUtil.format(new Date(), "yyyy/MM/dd HH:mm:ss");
	}
	
	public static String logtimeString(Date date){
		return DateUtil.format(date, "yyyy/MM/dd HH:mm:ss");
	}

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getTime() {
        return time;
    }

	public void setTime(Date time) {
		this.time = time;
	}

}
