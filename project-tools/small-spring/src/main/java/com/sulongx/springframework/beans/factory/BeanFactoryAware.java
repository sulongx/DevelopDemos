package com.sulongx.springframework.beans.factory;

import com.sulongx.springframework.beans.exception.BeansException;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2022/11/17 18:12
 **/
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
