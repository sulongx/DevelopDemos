package com.sulongx.springframework.beans.factory.support;

import com.sulongx.springframework.beans.exception.BeansException;
import com.sulongx.springframework.core.io.Resource;
import com.sulongx.springframework.core.io.ResourceLoader;

/**
 * @author sulongx
 * @title BeanDefinition读取
 * @details
 * @date 2022/10/30
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
}
