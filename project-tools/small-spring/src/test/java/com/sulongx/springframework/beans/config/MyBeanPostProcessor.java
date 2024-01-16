package com.sulongx.springframework.beans.config;

import com.sulongx.springframework.beans.bean.UserService;
import com.sulongx.springframework.beans.exception.BeansException;
import com.sulongx.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2022/10/31 16:26
 **/
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("userService".equals(beanName)){
            UserService userService = (UserService) bean;
            userService.setMsg("修改: 测试内容2");
        }
        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
