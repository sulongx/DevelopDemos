package com.sulongx.springframework.beans.factory.support;

import com.sulongx.springframework.beans.BeansException;
import com.sulongx.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author sulongx
 * @version 1.0
 * @description 实例化Bean
 * @date 2022/10/28 16:47
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {


    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        }catch (InstantiationException | IllegalAccessException e){
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }


    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
        }catch (Exception e){
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args){
        Constructor<?> constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for(Constructor<?> ctor : declaredConstructors){
            if(args != null && ctor.getParameterTypes().length == args.length){
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }


    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
