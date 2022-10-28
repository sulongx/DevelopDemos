package com.sulongx.springframework.beans.factory.config;

/**
 * @author sulongx
 * @version 1.0
 * @description 单例bean注册接口
 * @date 2022/10/28 16:46
 **/
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
