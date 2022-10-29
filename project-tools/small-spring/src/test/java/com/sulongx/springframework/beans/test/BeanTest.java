package com.sulongx.springframework.beans.test;

import com.sulongx.springframework.beans.bean.UserDao;
import com.sulongx.springframework.beans.bean.UserService;
import com.sulongx.springframework.beans.factory.PropertyValue;
import com.sulongx.springframework.beans.factory.PropertyValues;
import com.sulongx.springframework.beans.factory.config.BeanDefinition;
import com.sulongx.springframework.beans.factory.config.BeanReference;
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


    @Test
    public void testProperty(){
        //初始化Bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //Bean注册
        beanFactory.registryBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        PropertyValues userPropertyValues = new PropertyValues();
        userPropertyValues.addPropertyValue(new PropertyValue("name", "用户服务层"));
        userPropertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        beanFactory.registryBeanDefinition("userService", new BeanDefinition(UserService.class, userPropertyValues));

        //获取Bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        System.out.println(userService.getUserName("1"));
    }
}
