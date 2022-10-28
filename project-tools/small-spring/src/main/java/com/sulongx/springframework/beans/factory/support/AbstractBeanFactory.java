package com.sulongx.springframework.beans.factory.support;

import com.sulongx.springframework.beans.BeansException;
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

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

}
