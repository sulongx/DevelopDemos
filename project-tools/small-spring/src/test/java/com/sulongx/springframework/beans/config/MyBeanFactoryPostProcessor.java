package com.sulongx.springframework.beans.config;

import com.sulongx.springframework.beans.exception.BeansException;
import com.sulongx.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.sulongx.springframework.beans.factory.PropertyValue;
import com.sulongx.springframework.beans.factory.PropertyValues;
import com.sulongx.springframework.beans.factory.config.BeanDefinition;
import com.sulongx.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.sulongx.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2022/10/31 16:23
 **/
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition userServiceBeanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = userServiceBeanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "修改:用户服务2!"));
    }
}
