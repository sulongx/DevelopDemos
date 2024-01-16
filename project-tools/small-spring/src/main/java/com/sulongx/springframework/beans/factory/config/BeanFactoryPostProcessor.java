package com.sulongx.springframework.beans.factory.config;

import com.sulongx.springframework.beans.exception.BeansException;
import com.sulongx.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2022/10/31 14:38
 **/
public interface BeanFactoryPostProcessor {

    /**
     * 在加载所有BeanDefinition后，实例化Bean对象之前，提供修改BeanDefinition属性的接口
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
