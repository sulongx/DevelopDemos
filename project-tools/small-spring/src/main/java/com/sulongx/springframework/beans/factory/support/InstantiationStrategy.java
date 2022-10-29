package com.sulongx.springframework.beans.factory.support;

import com.sulongx.springframework.beans.BeansException;
import com.sulongx.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author sulongx
 * @title  实例化策略
 * @details
 * @date 2022/10/29
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
