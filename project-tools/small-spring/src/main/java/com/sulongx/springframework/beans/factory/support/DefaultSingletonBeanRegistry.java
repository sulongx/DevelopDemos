package com.sulongx.springframework.beans.factory.support;

import com.sulongx.springframework.beans.exception.BeansException;
import com.sulongx.springframework.beans.factory.DisposableBean;
import com.sulongx.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author sulongx
 * @version 1.0
 * @description 默认单例bean注册实现
 * @date 2022/10/28 16:51
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {


    protected static final Object NULL_OBJECT = new Object();

    private final Map<String, Object> singletonObjects = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject){
        this.singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean){
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons(){
        Set<String> beanNameKeys = this.disposableBeans.keySet();
        String[] beanNames = beanNameKeys.toArray(new String[]{});
        for(String beanName : beanNames){
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
