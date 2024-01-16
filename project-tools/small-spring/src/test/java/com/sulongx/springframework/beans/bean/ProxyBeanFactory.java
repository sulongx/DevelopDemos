package com.sulongx.springframework.beans.bean;

import com.sulongx.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2022/11/22 15:19
 **/
public class ProxyBeanFactory implements FactoryBean<IUserDao> {

    @Override
    public IUserDao getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {
            System.out.println("代理方法["+ method.getName() +"]代理执行....");
            //构建数据源
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("1", "张三");
            hashMap.put("2", "李四");
            hashMap.put("3", "王二");

            return hashMap.get(args[0].toString());
        };
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);
    }

    @Override
    public Class<IUserDao> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
