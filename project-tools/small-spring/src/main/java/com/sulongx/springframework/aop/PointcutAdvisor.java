package com.sulongx.springframework.aop;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2023/2/19
 */
public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();
}
