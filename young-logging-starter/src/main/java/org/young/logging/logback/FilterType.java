package org.young.logging.logback;

public enum FilterType {
	
	Default(FilterDefaultAppender.class), 
	Elasticsearch(FilterElasticsearchAppender.class), 
	Rabbitmq(FilterRabbitMQAppender.class);
	
	private FilterType(Class<? extends org.young.logging.logback.FilterDefaultAppender> classType) {
		this.classType = classType;
	}
	private Class<? extends FilterDefaultAppender> classType;
	
	public Class<? extends FilterDefaultAppender> getClassType() {
		return classType;
	}
	
}
