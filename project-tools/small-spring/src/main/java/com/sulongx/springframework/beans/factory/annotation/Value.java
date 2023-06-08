package com.sulongx.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author xiongsulong
 * @desc 属性自动注入
 * @date 2023/6/7 20:51
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {
    String value();
}
