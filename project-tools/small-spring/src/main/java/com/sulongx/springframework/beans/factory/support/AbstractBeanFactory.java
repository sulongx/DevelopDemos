package com.sulongx.springframework.beans.factory.support;

import com.sulongx.springframework.beans.exception.BeansException;
import com.sulongx.springframework.beans.factory.BeanFactory;
import com.sulongx.springframework.beans.factory.config.BeanDefinition;

/**
 * @author sulongx
 * @version 1.0
 * @description 抽象Bean工厂-模板方法
 * @date 2022/10/28 16:49
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {


    @Override
    public Object getBean(String beanName) {
        Object bean = getSingleton(beanName);
        if(bean != null){
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }


    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return doGetBean(beanName, args);
    }

    protected <T> T doGetBean(final String name, final Object[] args){
        Object bean = getSingleton(name);
        if(bean != null){
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }



    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
