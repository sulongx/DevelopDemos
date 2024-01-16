package com.sulongx.springframework.beans.factory.config;

import com.sulongx.springframework.beans.exception.BeansException;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2022/10/31 14:43
 **/
public interface BeanPostProcessor {

    Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException;

    Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException;
}
