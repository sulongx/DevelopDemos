package com.sulongx.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * @author xiongsulong
 * @desc 拦截注解-作为Bean对象的作用域
 * @date 2023/5/31 00:12
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {
    String value() default "singleton";
}
