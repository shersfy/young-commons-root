package org.young.logging.logback;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.young.commons.protocols.ErrorLog;
import org.young.commons.protocols.ErrorLog.Location;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

/**
 * 默认Appender
 * @author pengy
 * @date 2018年12月14日
 */
public class FilterDefaultAppender extends UnsynchronizedAppenderBase<LoggingEvent> {
	
	protected static Logger LOGGER = LoggerFactory.getLogger(FilterElasticsearchAppender.class);

	private FilterType filterType;
	
	private FilterDefaultAppender appender;
	
	private String applicationName;
	
	protected LoggingProperties properties;
	
	@Override
	public void start() {
		try {
			Class<? extends FilterDefaultAppender> clazz = filterType.getClassType();
			appender = clazz.newInstance();
		} catch (Exception e) {
			LOGGER.error("", e);
		}
		super.start();
	}
	
	@Override
	protected void append(LoggingEvent event) {
		if (appender.getClass().isAssignableFrom(FilterDefaultAppender.class)) {
			// ignore
			return;
		}
		try {
			if (properties == null) {
				properties = ApplicationContextProvider.getBean(LoggingProperties.class);
			}
			if (properties == null) {
				return;
			}
			appender.properties = this.properties;
			appender.applicationName = this.applicationName;
		} catch (NoSuchBeanDefinitionException ex) {
			LOGGER.warn("NoSuchBeanDefinitionException", ex.getMessage());
			return;
		}
		appender.append(event);
	}
	
	protected ErrorLogExt initLog(LoggingEvent event) {

		ErrorLogExt log = new ErrorLogExt();
		log.setClassName(ErrorLog.class.getName());
		log.setApplicationName(getApplicationName());
		log.setId(UUID.randomUUID().toString());
		log.setLevel(event.getLevel().toString());
		log.setMessage(event.getFormattedMessage());
		log.setThreadName(event.getThreadName());
		log.setLocation(getLocation(event).toString());
		log.setRpcId(getRpcId(event));
		log.setTraceId(getTraceId(event));
		log.setThrowable(getThrowable(event));
		log.setThrowableDetail(getThrowableDetail(event));
		log.setIp(new Address().convert(event));

		log.setTimestamp(event.getTimeStamp());
		log.setResourcePool(properties.getResourcePool());
		log.setTenantId(properties.getTenantId());
		log.setTenantName(properties.getTenantName());
		log.setTenementInfo(properties.getTenantName());
		
		try {
			log.setHost(InetAddress.getLocalHost().getHostName());
		} catch (Exception e) {
			LOGGER.warn("", e);
		}
		return log;
	}
	
	static class Address extends ClassicConverter {
		@Override
		public String convert(ILoggingEvent iLoggingEvent) {
			try {
				return InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	private String getRpcId(LoggingEvent event) {
		Map<String, String> mdcPropertyMap = event.getMDCPropertyMap();
		return mdcPropertyMap.get("rpcId");
	}

	private String getTraceId(LoggingEvent event) {
		Map<String, String> mdcPropertyMap = event.getMDCPropertyMap();
		return mdcPropertyMap.get("traceId");
	}

	private String getThrowableDetail(LoggingEvent event) {
		IThrowableProxy tp = event.getThrowableProxy();
		if (tp == null)
			return "";

		StringBuilder sb = new StringBuilder(2048);
		while (tp != null) {

			ThrowableProxyUtil.subjoinFirstLine(sb, tp);

			int commonFrames = tp.getCommonFrames();
			StackTraceElementProxy[] stepArray = tp.getStackTraceElementProxyArray();
			for (int i = 0; i < stepArray.length - commonFrames; i++) {
				sb.append("\n");
				sb.append(CoreConstants.TAB);
				ThrowableProxyUtil.subjoinSTEP(sb, stepArray[i]);
			}

			if (commonFrames > 0) {
				sb.append("\n");
				sb.append(CoreConstants.TAB).append("... ").append(commonFrames).append(" common frames omitted");
			}

			sb.append("\n");

			tp = tp.getCause();
		}
		return sb.toString();
	}


	private String getThrowable(LoggingEvent event) {
		IThrowableProxy tp = event.getThrowableProxy();
		if (tp == null){
			return "";
		}
		StringBuilder sb = new StringBuilder(2048);
		sb.append(tp.getClassName());
		return sb.toString();
	}

	private Location getLocation(LoggingEvent event) {
		Location location = new Location();
		StackTraceElement[] cda = event.getCallerData();
		if (cda != null && cda.length > 0) {
			StackTraceElement immediateCallerData = cda[0];
			location.setClassName(immediateCallerData.getClassName());
			location.setMethodName(immediateCallerData.getMethodName());
			location.setFileName(immediateCallerData.getFileName());
			location.setLineNumber(String.valueOf(immediateCallerData.getLineNumber()));
		}
		return location;
	}

	public FilterType getFilterType() {
		return filterType;
	}

	public void setFilterType(String filterType) {
		String first = filterType.substring(0, 1);
		filterType   = first.toUpperCase()+filterType.substring(1);
		this.filterType = FilterType.valueOf(filterType);
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

}
