package com.sulongx.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author xiongsulong
 * @desc 限定符
 * @date 2023/6/7 20:53
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {
    String value() default "";
}
