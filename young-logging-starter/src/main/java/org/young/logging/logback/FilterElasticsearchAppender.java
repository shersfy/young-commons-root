package org.young.logging.logback;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.young.commons.protocols.ErrorLog;

import ch.qos.logback.classic.spi.LoggingEvent;

/**
 * FilterElasticsearchAppender
 * @author pengy
 * @date 2018年12月18日
 */
public class FilterElasticsearchAppender extends FilterDefaultAppender {

	private ElasticsearchTemplate elasticsearchTemplate = null;

	@Override
	protected void append(LoggingEvent event) {
		
		try {
			if (elasticsearchTemplate == null) {
				elasticsearchTemplate = ApplicationContextProvider.getBean(ElasticsearchTemplate.class);
				LOGGER.info("ElasticsearchTemplate initialized");
			}
			if (elasticsearchTemplate == null) {
				return;
			}
		} catch (NoSuchBeanDefinitionException ex) {
			LOGGER.warn("NoSuchBeanDefinitionException", ex.getMessage());
			return;
		}
		

		try {
			ErrorLog log = initLog(event);
			IndexQuery indexQuery = new IndexQueryBuilder().withId(log.getId()).withObject(log).build();
			String id = elasticsearchTemplate.index(indexQuery);
			LOGGER.info("Insert elasticsearch successful , id={}", id);
		} catch (Exception e) {
			LOGGER.warn("ElasticsearchAppender append error", e);
		}
		
	}

}
