package com.sulongx.springframework.beans.factory;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2022/11/17 18:20
 **/
public interface BeanClassLoaderAware extends Aware{

    void setBeanClassLoader(ClassLoader classLoader);
}
