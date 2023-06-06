package com.sulongx.springframework.stereotype;

import java.lang.annotation.*;

/**
 * @author xiongsulong
 * @desc 类Bean注解器
 * @date 2023/5/31 00:19
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    String value() default "";
}
