package org.young.commons.protocols;

import org.young.commons.beans.BaseBean;

public class ErrorLog extends BaseProtocol{
	
	private static final long serialVersionUID = 1L;

	/**获得日志输出IP*/
	private String ip;
	
	/**获得日志输出hostname*/
	private String host;
	
	/**获得应用名称信息*/
	private String applicationName;
	
	/**获得日志等级信息*/
	private String level;
	
	/**获得线程信息*/
	private String threadName;
	
	/**获得日志信息*/
	private String message;
	
	/**获得异常信息*/
	private String throwable;
	
	/**获得异常详细信息*/
	private String throwableDetail;

	/**获得调用信息*/
	private String location;
	
	/**获得traceId*/
	private String traceId;
	
	/**获得rpcId*/
	private String rpcId;
	
	/**获得租户信息*/
    private String tenementInfo;
	
	/**获得资源池信息*/
	private String resourcePool;
	
	
	public ErrorLog() {
		super();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getThrowable() {
		return throwable;
	}

	public void setThrowable(String throwable) {
		this.throwable = throwable;
	}

	public String getThrowableDetail() {
		return throwableDetail;
	}

	public void setThrowableDetail(String throwableDetail) {
		this.throwableDetail = throwableDetail;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public String getRpcId() {
		return rpcId;
	}

	public void setRpcId(String rpcId) {
		this.rpcId = rpcId;
	}

	public String getTenementInfo() {
		return tenementInfo;
	}

	public void setTenementInfo(String tenementInfo) {
		this.tenementInfo = tenementInfo;
	}

	public String getResourcePool() {
		return resourcePool;
	}

	public void setResourcePool(String resourcePool) {
		this.resourcePool = resourcePool;
	}
	
	public static class Location extends BaseBean{
		
		private static final long serialVersionUID = 1L;

		private String className;
		
		private String methodName;
		
		private String fileName;
		
		private String LineNumber;

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

		public String getMethodName() {
			return methodName;
		}

		public void setMethodName(String methodName) {
			this.methodName = methodName;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getLineNumber() {
			return LineNumber;
		}

		public void setLineNumber(String lineNumber) {
			LineNumber = lineNumber;
		}
	}
}
