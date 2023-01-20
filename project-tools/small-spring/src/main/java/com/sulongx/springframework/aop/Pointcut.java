package com.sulongx.springframework.aop;

/**
 * @author sulongx
 * @title  切点
 * @details
 * @date 2022/11/28
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
