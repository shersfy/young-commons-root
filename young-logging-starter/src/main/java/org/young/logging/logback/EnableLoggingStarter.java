package org.young.logging.logback;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/***
 * 启用日志组件配置
 * @author pengy
 * @date 2018年12月19日
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(LoggingAutoConfig.class)
@Documented
@Inherited
public @interface EnableLoggingStarter {

}
