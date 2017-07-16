package com.wilddog.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于注解使用Widddog的节点
 * @author yellowcong
 * @date 2017年7月1日
 */
@Target({ java.lang.annotation.ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface WilddogTable {
	
	//使用这个默认配置即可
	public String value() default "";
	
	/**
	 * 名称
	 * @return
	 */
	public String name() default "";
}
