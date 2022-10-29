package com.sulongx.springframework.beans.factory;

import com.sulongx.springframework.beans.exception.BeansException;

/**
 * @author sulongx
 * @version 1.0
 * @description bean工厂接口
 * @date 2022/10/28 16:52
 **/
public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;

    Object getBean(String beanName, Object ... args) throws BeansException;
}
