package com.sulongx.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author sulongx
 * @title 包装切面通知信息
 * @details
 * @date 2022/12/3
 */
public class AdvisedSupport {

    //被代理目标对象
    private TargetSource targetSource;

    //方法拦截器
    private MethodInterceptor methodInterceptor;

    //方法匹配器
    private MethodMatcher methodMatcher;

    private boolean proxyTargetClass = false;

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    public boolean isProxyTargetClass(){
        return proxyTargetClass;
    }


    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
