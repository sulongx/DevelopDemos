package com.sulongx.patterns.proxypattern.dynamicproxypatterns;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 描述:
 *
 * @author xiongsulong
 * @create 2020-10-28 21:23
 */
public class StuInvocationHandler<T> implements InvocationHandler {

    T target;

    public StuInvocationHandler(T target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(target, args);
        return result;
    }
}
