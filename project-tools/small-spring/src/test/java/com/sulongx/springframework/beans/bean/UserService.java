package com.sulongx.springframework.beans.bean;

import com.sulongx.springframework.beans.exception.BeansException;
import com.sulongx.springframework.beans.factory.*;
import com.sulongx.springframework.context.ApplicationContext;
import com.sulongx.springframework.context.ApplicationContextAware;

/**
 * @author sulongx
 * @version 1.0
 * @description 用户服务Bean类
 * @date 2022/10/28 17:54
 **/
public class UserService implements InitializingBean, DisposableBean, ApplicationContextAware, BeanFactoryAware, BeanNameAware, BeanClassLoaderAware {

    public ApplicationContext applicationContext;

    public BeanFactory beanFactory;

    private String name;

    private String msg;

    private UserDao userDao;

    public UserService() {
    }



    public void getInfo(){
        System.out.println("获取用户信息:");
        System.out.println(toString());
    }


    public String getUserName(String id){
        return userDao.queryUserName(id);
    }


    @Override
    public void destroy() throws Exception {
        System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName()  + ":执行销毁方法...[实现接口方式]");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName()  + ":执行初始化后方法...[实现接口方式]");
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("classLoader: " + classLoader);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("beanName: " + name);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
