package com.sulongx.springframework.beans.factory;

import com.sulongx.springframework.beans.exception.BeansException;

import java.util.Map;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2022/10/30
 */
public interface ListableBeanFactory extends BeanFactory{

    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    String[] getBeanDefinitionNames();
}
