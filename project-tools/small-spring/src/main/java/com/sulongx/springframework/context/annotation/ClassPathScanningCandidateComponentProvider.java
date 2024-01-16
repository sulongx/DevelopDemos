package com.sulongx.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.sulongx.springframework.beans.factory.config.BeanDefinition;
import com.sulongx.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author xiongsulong
 * @desc 对象扫描装配
 * @date 2023/5/31 00:22
 */
public class ClassPathScanningCandidateComponentProvider {
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
