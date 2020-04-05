package org.young.commons.constant;

public final class Const {
	
	private Const(){
	}

	/**最小时间1900-01-01 0:0:0.0**/
	public static final long MIN_DATE = -2209017600000L;
	/**最大时间9999-12-31 23:59:59.999**/
	public static final long MAX_DATE = 253402271999999L;
	/**年份类型: yyyy**/
	public static final String yyyy     = "yyyy";
	/**年月类型: yyyyMM**/
	public static final String yyyyMM   = "yyyy/MM";
	/**日期统一格式:yyyy/MM/dd**/
	public static final String yyyyMMdd         = "yyyy/MM/dd";
	/**文件夹时间戳格式:yyyyMMddHHmmssSSS**/
	public static final String FOLDER_TIMESTAMP = "yyyyMMddHHmmssSSS";
	/**数据转string日期格式:yyyy-MM-dd**/
	public static final String FORMAT_DATE      = "yyyy-MM-dd";
	/**数据转string日期时间格式:yyyy-MM-dd HH:mm:ss**/
	public static final String FORMAT_DATETIME  = "yyyy-MM-dd HH:mm:ss";
	/**数据转string日期时间格式:yyyy-MM-dd HH:mm:ss.SSS**/
	public static final String FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";
	/**日期时间统一格式:yyyy/MM/dd HH:mm:ss**/
	public static final String yyyyMMddHHmmss   = "yyyy/MM/dd HH:mm:ss";
	/**日期时间统一格式:yyyy/MM/dd HH:mm:ss.SSS**/
	public static final String yyyyMMddHHmmssSSS   = "yyyy/MM/dd HH:mm:ss.SSS";
	/**时间统一格式:HH:mm:ss**/
	public static final String HHmmss           = "HH:mm:ss";
	/**浮点型小数位默认保留30位数**/
	public static final String DEC_FORMAT       = "#.##############################";
	/**超时时间30秒**/
	public static final long CONNECT_TIMEOUT	= 30 * 1000;

	/**系统换行符**/
	public static final String LINE_SEP     = System.getProperty("line.separator", "\n");
	/**系统换行符匹配**/
	public static final String LINE_SEP_REG = "\r\n|\n|\r";
	public static final String COLUMN_SEP   = "\u0001";
	/**最大分页大小**/
	public static int MAX_PAGE_SIZE = 20;
	
	public static enum AccessKeyCode {
		gateway,
		openapi
	}

}
