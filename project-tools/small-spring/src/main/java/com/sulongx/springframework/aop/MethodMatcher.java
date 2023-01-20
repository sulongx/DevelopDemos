package com.sulongx.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2022/11/28
 */
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);
}
