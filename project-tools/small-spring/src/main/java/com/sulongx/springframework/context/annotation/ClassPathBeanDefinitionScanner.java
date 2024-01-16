package com.sulongx.springframework.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.sulongx.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.sulongx.springframework.beans.factory.config.BeanDefinition;
import com.sulongx.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.sulongx.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author xiongsulong
 * @desc BeanDefinition扫描器
 * @date 2023/5/31 00:36
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {
    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            //解析Bean的作用域
            for (BeanDefinition beanDefinition : candidates) {
                String scope = resolveBeanScope(beanDefinition);
                if (StrUtil.isNotEmpty(scope)) {
                    beanDefinition.setScope(scope);
                }
                registry.registryBeanDefinition((determineBeanName(beanDefinition)), beanDefinition);
            }
        }
        //注解注入扫描器注册
        registry.registryBeanDefinition("com.sulongx.springframework.beans.factory.annotation.internalAutowiredAnnotationBeanPostProcessor", new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }

    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (scope != null) {
            return scope.value();
        }
        return StrUtil.EMPTY;
    }

    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isNotEmpty(value)) {
            return value;
        }
        return StrUtil.lowerFirst(beanClass.getSimpleName());
    }
}
