package com.sulongx.springframework.context.support;

import com.sulongx.springframework.beans.exception.BeansException;
import com.sulongx.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.sulongx.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.sulongx.springframework.beans.factory.config.BeanPostProcessor;
import com.sulongx.springframework.context.ConfigurableApplicationContext;
import com.sulongx.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2022/10/31 14:50
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {
        //创建BeanFactory,并加载BeanDefinition
        refreshBeanFactory();
        //获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        //添加ApplicationContextAwareProcessor
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        //在Bean实例化之前,执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);
        //BeanPostProcessor需要在Bean初始化之前注册
        registerBeanPostProcessor(beanFactory);
        //初始化Bean单例对象
        beanFactory.preInstantiationSingletons();
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for(BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()){
            beanFactoryPostProcessor.postProcessorBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessor(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for(BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()){
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }


    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }
}
