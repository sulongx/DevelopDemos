package com.sulongx.springframework.beans.factory;

import com.sulongx.springframework.beans.exception.BeansException;
import com.sulongx.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.sulongx.springframework.beans.factory.config.BeanDefinition;
import com.sulongx.springframework.beans.factory.config.BeanPostProcessor;
import com.sulongx.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2022/10/30
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 初始化所有已经注册过的单例Bean
     * @throws BeansException
     */
    void preInstantiationSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
