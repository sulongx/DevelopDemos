package com.sulongx.springframework.beans.factory.support;

import com.sulongx.springframework.beans.exception.BeansException;
import com.sulongx.springframework.beans.factory.config.BeanDefinition;
import net.bytebuddy.ByteBuddy;

import java.lang.reflect.Constructor;

/**
 * @Description
 * @Author sulongx
 * @Date 4/22/23 6:37 PM
 * @Version 1.0
 **/
public class ByteBuddyInstantiationStrategy implements InstantiationStrategy{


    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        try {
            ByteBuddy byteBuddy = new ByteBuddy();
            Class bean = byteBuddy
                    .subclass(beanDefinition.getBeanClass())
                    .make()
                    .load(getClass().getClassLoader())
                    .getLoaded();
            if(ctor == null){
                return bean.newInstance();
            }else {
                return bean.getConstructor(ctor.getParameterTypes()).newInstance(args);
            }
        }catch (Exception e){
            throw new BeansException("Failed to instantiation [" + beanDefinition.getBeanClass().getName() + "]", e);
        }
    }


}
