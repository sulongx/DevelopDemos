package com.sulongx.patterns.proxypattern.dynamicproxypatterns;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 描述:
 *
 * @author xiongsulong
 * @create 2020-10-28 21:26
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        //被代理对象
        Person caocao = new Student("曹操");

        //创建一个与被代理对象相关联的InvocationHandler
        InvocationHandler handler = new StuInvocationHandler<Person>(caocao);

        //创建代理执行方法
        Person stuProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(),
                new Class[]{Person.class}, handler);
        stuProxy.giveMoney();
        stuProxy.study();
    }
}
