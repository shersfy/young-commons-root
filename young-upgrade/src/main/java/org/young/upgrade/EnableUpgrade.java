package org.young.upgrade;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/***
 * 开启版升级功能
 * @author pengy
 * @date 2018年11月10日
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(UpgradeAutoConfiguration.class)
@Documented
@Inherited
public @interface EnableUpgrade {

}
