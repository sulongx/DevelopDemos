package com.sulongx.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2023/2/19
 */
public interface Advisor {

    Advice getAdvice();
}
