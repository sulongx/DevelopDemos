package com.sulongx.springframework.beans.factory.config;

import com.sulongx.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2022/10/30
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
