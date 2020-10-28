package com.sulongx.patterns.proxypattern;

/**
 * 描述:
 * 真实主题
 *
 * @author xiongsulong
 * @create 2020-10-28 20:26
 */
public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("访问真实主题的方法!");
    }
}
