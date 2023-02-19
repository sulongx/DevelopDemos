package com.sulongx.springframework.beans.factory.config;

import com.sulongx.springframework.beans.exception.BeansException;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2023/2/19
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{


    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;
}
