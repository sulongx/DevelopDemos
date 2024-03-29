package com.sulongx.springframework.beans.factory.config;

import com.sulongx.springframework.beans.factory.HierarchicalBeanFactory;
import com.sulongx.springframework.util.StringValueResolver;

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

    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);
}
