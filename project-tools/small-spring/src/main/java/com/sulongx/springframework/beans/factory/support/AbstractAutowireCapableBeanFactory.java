package com.sulongx.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.sulongx.springframework.beans.exception.BeansException;
import com.sulongx.springframework.beans.factory.*;
import com.sulongx.springframework.beans.factory.config.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author sulongx
 * @version 1.0
 * @description 实例化Bean
 * @date 2022/10/28 16:47
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {


    private InstantiationStrategy instantiationStrategy = new ByteBuddyInstantiationStrategy();


    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean;
        try {
            //判断是否为代理bean
            bean = resolveBeforeInstantiation(beanName, beanDefinition);
            if(bean != null){
                return bean;
            }
            //实例化Bean
            bean = createBeanInstance(beanDefinition, beanName, args);
            //属性值注入替换
            applyBeanPostProcessorsBeforeApplyingPropertyValues(beanName, bean, beanDefinition);
            //Bean属性填充
            applyPropertyValues(beanName, bean, beanDefinition);
            //执行Bean的初始化方法和BeanPostProcessor的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        }catch (Exception e){
            throw new BeansException("Instantiation of bean failed", e);
        }

        //注册实现了 DisposableBean 接口的 Bean 对象
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        if(beanDefinition.isSingleton()){
            addSingleton(beanName, bean);
        }

        return bean;
    }

    protected void applyBeanPostProcessorsBeforeApplyingPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){
            if(beanPostProcessor instanceof InstantiationAwareBeanPostProcessor instantiationAwareBeanPostProcessor){
                PropertyValues pvs = instantiationAwareBeanPostProcessor.postProcessPropertyValues(beanDefinition.getPropertyValues(), bean, beanName);
                if(pvs != null){
                    for (PropertyValue propertyValue : pvs.getPropertyValues()){
                        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                    }
                }
            }
        }
    }

    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition){
        Object bean = applyBeanPostProcessorsBeforeInstantiation(beanDefinition.getBeanClass(), beanName);
        if (null != bean) {
            bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        }
        return bean;
    }

    protected Object applyBeanPostProcessorsBeforeInstantiation(Class<?> beanClass, String beanName){
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){
            if(beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                Object result = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessBeforeInstantiation(beanClass, beanName);
                if (null != result) {
                    return result;
                }
            }
        }
        return null;
    }

    private void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition){
        if(!beanDefinition.isSingleton()){
            return;
        }
        if(bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())){
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition){
        if(bean instanceof Aware){
            if(bean instanceof BeanFactoryAware){
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if(bean instanceof BeanClassLoaderAware){
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
            if(bean instanceof BeanNameAware){
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }
        //执行BeanPostProcessor Before处理
        Object wrapperBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        try {
            invokeInitMethods(beanName, wrapperBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }
        //执行BeanPostProcessor After处理
        wrapperBean = applyBeanPostProcessorsAfterInitialization(wrapperBean, beanName);
        return wrapperBean;
    }


    private void invokeInitMethods(String beanName, Object wrapperBean, BeanDefinition beanDefinition) throws Exception{
        if(wrapperBean instanceof InitializingBean){
            ((InitializingBean) wrapperBean).afterPropertiesSet();
        }else {
            String initMethodName = beanDefinition.getInitMethodName();
            if(StrUtil.isNotBlank(initMethodName)){
                Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
                initMethod.invoke(wrapperBean);
            }
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for(BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){
            Object current = beanPostProcessor.postProcessorBeforeInitialization(existingBean, beanName);
            if(current == null){
                continue;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for(BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){
            Object current = beanPostProcessor.postProcessorAfterInitialization(existingBean, beanName);
            if(current == null){
                continue;
            }
            result = current;
        }
        return result;
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

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for(PropertyValue pv : propertyValues.getPropertyValues()){
                String name = pv.getName();
                Object value = pv.getValue();
                if(value instanceof BeanReference beanReference){
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean, name, value);
            }
        }catch (Exception e){
            throw new BeansException("Error setting property values: " + beanName, e);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
