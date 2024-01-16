package com.sulongx.springframework.beans.aop;

import com.sulongx.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class UserServiceBeforeAdvices implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截的方法: methodName: " + method.getName());
    }
}
