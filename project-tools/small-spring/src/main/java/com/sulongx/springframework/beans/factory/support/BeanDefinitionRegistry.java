package com.sulongx.springframework.beans.factory.support;

import com.sulongx.springframework.beans.factory.config.BeanDefinition;

/**
 * @author sulongx
 * @version 1.0
 * @description BeanDefinition注册
 * @date 2022/10/28 16:50
 **/
public interface BeanDefinitionRegistry {
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
