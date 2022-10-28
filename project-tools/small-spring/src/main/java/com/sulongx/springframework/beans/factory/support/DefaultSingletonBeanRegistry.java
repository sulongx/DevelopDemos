package com.sulongx.springframework.beans.factory.support;

import com.sulongx.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sulongx
 * @version 1.0
 * @description 默认单例bean注册实现
 * @date 2022/10/28 16:51
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject){
        this.singletonObjects.put(beanName, singletonObject);
    }
}
