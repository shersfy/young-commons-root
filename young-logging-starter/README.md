# young-logging-starter
## 日志采集客户端
### 1. 添加依赖
```
	<dependency>
		<groupId>org.young</groupId>
		<artifactId>young-logging-starter</artifactId>
		<version>${project.version}</version>
	</dependency>
```
### 2. 添加注解@EnableLoggingStarter
```
	@EnableLoggingStarter
	@SpringBootApplication
	public class Application{
	}

```
### 3. 修改配置
bootstrap.yml中加入以下配置

```
# 说明：配置过滤器类型，被过滤器拦截到的日志将通过Appender适配发送到响应的位置
# 不发送（默认配置）：Default
# ES适配，环境配置中需要配置ES连接信息：Elasticsearch
# RabbitMQ适配，环境配置中需要配置RabbitMQ连接信息：Rabbitmq

# logging
logging:
  path: /young/logs/${spring.application.name}
  level:
    root: info
  young:
    filter:
      level: error
      type: rabbitmq
      resource-pool: young-pool
      tenant-name: young系统
```