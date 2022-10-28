package com.sulongx.springframework.beans.factory.support;

import com.sulongx.springframework.beans.BeansException;
import com.sulongx.springframework.beans.factory.config.BeanDefinition;

/**
 * @author sulongx
 * @version 1.0
 * @description 实例化Bean
 * @date 2022/10/28 16:47
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        }catch (InstantiationException | IllegalAccessException e){
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }
}
