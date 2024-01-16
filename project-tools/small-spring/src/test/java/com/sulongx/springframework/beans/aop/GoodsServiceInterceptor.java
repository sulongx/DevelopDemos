package com.sulongx.springframework.beans.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author sulongx
 * @title 商品服务拦截器
 * @details
 * @date 2022/12/11
 */
public class GoodsServiceInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            return methodInvocation.proceed();
        }finally {
            System.out.println("方法耗时: " + (System.currentTimeMillis() - startTime) + "ms");
            System.out.println("AOP拦截器拦截");
            System.out.println("拦截方法: " + methodInvocation.getMethod().getName());
        }
    }
}
