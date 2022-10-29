package com.sulongx.springframework.beans.test;

import com.sulongx.springframework.beans.bean.UserService;
import com.sulongx.springframework.beans.factory.config.BeanDefinition;
import com.sulongx.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * @author sulongx
 * @version 1.0
 * @description 测试类
 * @date 2022/10/28 17:52
 **/
public class BeanTest {

    @Test
    public void test(){
        //初始化bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService", beanDefinition);

        //第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", (Object) null);
        userService.getInfo();

        //第二次获取bean
        userService = (UserService) beanFactory.getBean("userService", "张三");
        userService.getInfo();
    }
}
