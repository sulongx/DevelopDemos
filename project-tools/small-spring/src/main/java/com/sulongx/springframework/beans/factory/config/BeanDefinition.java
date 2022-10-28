package com.sulongx.springframework.beans.factory.config;

/**
 * @author sulongx
 * @version 1.0
 * @description bean的定义
 * @date 2022/10/28 16:45
 **/
@SuppressWarnings({"rawtypes"})
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }


    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
