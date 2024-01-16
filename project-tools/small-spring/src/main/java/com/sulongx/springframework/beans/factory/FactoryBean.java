package com.sulongx.springframework.beans.factory;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2022/11/21 16:44
 **/
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<T> getObjectType();

    boolean isSingleton();
}
