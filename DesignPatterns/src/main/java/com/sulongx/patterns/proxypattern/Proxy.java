package com.sulongx.patterns.proxypattern;

/**
 * 描述:
 * 代理类
 *
 * @author xiongsulong
 * @create 2020-10-28 20:27
 */
public class Proxy implements Subject {

    private RealSubject realSubject;

    @Override
    public void request() {
        if(realSubject == null){
            realSubject = new RealSubject();
        }
        preRequest();
        realSubject.request();
        postRequest();

    }

    private void preRequest(){
        System.out.println("访问真实主题之前的预处理...");
    }

    private void postRequest(){
        System.out.println("访问真实主题之后的处理...");
    }
}
