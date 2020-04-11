package org.young.logging.logback;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.young.commons.protocols.ErrorLog;

import ch.qos.logback.classic.spi.LoggingEvent;

public class FilterRabbitMQAppender extends FilterDefaultAppender {
	
	private RabbitMessagingTemplate rabbitTemplate;

	@Override
	protected void append(LoggingEvent event) {
		try {
			if (rabbitTemplate == null) {
				rabbitTemplate = ApplicationContextProvider.getBean(RabbitMessagingTemplate.class);
				LOGGER.info("RabbitMessagingTemplate initialized");
			}
			if (rabbitTemplate == null) {
				return;
			}
		} catch (NoSuchBeanDefinitionException ex) {
			LOGGER.warn("NoSuchBeanDefinitionException", ex.getMessage());
			return;
		}
		
		// 发送日志
		ErrorLog log = initLog(event);
		rabbitTemplate.convertAndSend(properties.getSendLogQueue(), log.toString());
	}

}
