package com.sulongx.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2023/2/19
 */
public interface MethodBeforeAdvice extends BeforeAdvice{

    void before(Method method, Object[] args, Object target) throws Throwable;
}
