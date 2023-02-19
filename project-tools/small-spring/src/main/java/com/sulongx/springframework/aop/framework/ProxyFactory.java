package com.sulongx.springframework.aop.framework;

import com.sulongx.springframework.aop.AdvisedSupport;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2023/2/19
 */
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }


    public Object getProxy(){
        return createAopProxy().getProxy();
    }

    public AopProxy createAopProxy(){
        if(advisedSupport.isProxyTargetClass()){
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }

}
