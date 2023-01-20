package com.sulongx.springframework.aop;

/**
 * @author sulongx
 * @title 被代理的目标对象
 * @details
 * @date 2022/12/3
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass(){
        return this.target.getClass().getInterfaces();
    }

     public Object getTarget(){
        return this.target;
     }
}
