package com.sulongx.springframework.beans.factory.support;

import com.sulongx.springframework.beans.exception.BeansException;
import com.sulongx.springframework.beans.factory.config.BeanDefinition;

/**
 * @author sulongx
 * @version 1.0
 * @description BeanDefinition注册
 * @date 2022/10/28 16:50
 **/
public interface BeanDefinitionRegistry {
    /**
     * 注册表注册 BeanDefinition
     * @param beanName
     * @param beanDefinition
     */
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 通过Bean名查询BeanDefinition
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 是否包含指定名称的BeanDefinition
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 返回注册表中所有Bean名称
     * @return
     */
    String[] getBeanDefinitionNames();
}
