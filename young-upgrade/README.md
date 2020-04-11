# young-upgrade
### 版本自动升级程序，适用于spring-boot-2.2.5.RELEASE项目

#### 1. 添加依赖
```
<dependency>
	<groupId>org.young</groupId>
	<artifactId>young-upgrade</artifactId>
	<version>${project.version}</version>
</dependency>

```

#### 2. 添加注解
```
@EnableUpgrade
public class Application

```

#### 3. 修改配置
服务启动自动执行升级脚本，如果该版本已经升级，会跳过升级脚本
```
upgrade:
  enabled: true
  version: v1.0.3
  application-name: ${spring.application.name}
  upgrade-script: classpath:/upgrade/datahub_job_manager_v1.0.2-1.0.3.sql

```
