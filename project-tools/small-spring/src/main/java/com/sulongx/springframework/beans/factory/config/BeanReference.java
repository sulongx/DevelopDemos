package com.sulongx.springframework.beans.factory.config;

/**
 * @author sulongx
 * @title Bean引用
 * @details
 * @date 2022/10/29
 */
public class BeanReference {

    private String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
