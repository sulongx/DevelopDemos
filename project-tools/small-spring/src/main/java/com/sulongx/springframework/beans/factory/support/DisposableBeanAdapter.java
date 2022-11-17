package com.sulongx.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.sulongx.springframework.beans.factory.DisposableBean;
import com.sulongx.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author sulongx
 * @version 1.0
 * @description Bean销毁适配器
 * @date 2022/11/16 17:14
 **/
public class DisposableBeanAdapter implements DisposableBean {


    private final Object bean;

    private final String beanName;

    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if(bean instanceof DisposableBean){
            ((DisposableBean) bean).destroy();
        }
        if(StrUtil.isNotBlank(destroyMethodName) && !(bean instanceof DisposableBean && contains(DisposableBean.class.getMethods(), this.destroyMethodName))){
            Method method = bean.getClass().getMethod(destroyMethodName);
            method.invoke(bean);
        }
    }

    private boolean contains(Method[] methods, String name){
        for(Method method : methods){
            if(method.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
