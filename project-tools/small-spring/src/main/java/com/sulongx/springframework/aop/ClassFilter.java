package com.sulongx.springframework.aop;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2022/11/28
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
