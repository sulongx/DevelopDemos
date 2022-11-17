package com.sulongx.springframework.beans.test;

import com.sulongx.springframework.beans.bean.UserService;
import com.sulongx.springframework.beans.config.MyBeanFactoryPostProcessor;
import com.sulongx.springframework.beans.config.MyBeanPostProcessor;
import com.sulongx.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.sulongx.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.sulongx.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2022/10/31 16:32
 **/
public class ApplicationContextTest {

    @Test
    public void beanFactoryPostProcessorAndBeanPostProcessor_NoContextTest(){
        //初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //读取文件以及注册Bean
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        //BeanDefinition初始化之前，修改其属性值
        MyBeanFactoryPostProcessor myBeanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        myBeanFactoryPostProcessor.postProcessorBeanFactory(beanFactory);

        //Bean实例化之后，修改其属性值
        MyBeanPostProcessor myBeanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(myBeanPostProcessor);

        //获取Bean并调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        System.out.println(userService);
    }


    @Test
    public void beanFactoryPostProcessorAndBeanPostProcessor_ContextTest(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService);
    }


    @Test
    public void test_hook(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        //注册钩子方法
        applicationContext.registerShutdownHook();

        UserService userService = applicationContext.getBean("userService", UserService.class);
        String userName = userService.getUserName("1");
        System.out.println(userName);
    }
}
