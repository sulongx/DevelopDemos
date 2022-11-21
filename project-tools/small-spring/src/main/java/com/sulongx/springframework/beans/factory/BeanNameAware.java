package com.sulongx.springframework.beans.factory;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2022/11/17 18:24
 **/
public interface BeanNameAware extends Aware{

    void setBeanName(String name);

}
