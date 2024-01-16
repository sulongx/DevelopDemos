package com.sulongx.springframework.beans.test;

import com.sulongx.springframework.beans.bean.IGoodsService;
import com.sulongx.springframework.beans.bean.IUserDao;
import com.sulongx.springframework.beans.bean.UserService;
import com.sulongx.springframework.beans.bean.UserServiceV10;
import com.sulongx.springframework.beans.config.MyBeanFactoryPostProcessor;
import com.sulongx.springframework.beans.config.MyBeanPostProcessor;
import com.sulongx.springframework.beans.event.CustomEvent;
import com.sulongx.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.sulongx.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.sulongx.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import java.lang.reflect.Proxy;

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


    @Test
    public void test_aware(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        //注册钩子方法
        applicationContext.registerShutdownHook();

        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println("applicationContext: " + userService.getApplicationContext());
        System.out.println("beanFactory: " + userService.getBeanFactory());
    }


    @Test
    public void test_prototype(){
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserServiceV10 userServiceV10_1 = applicationContext.getBean("userServiceV10", UserServiceV10.class);
        UserServiceV10 userServiceV10_2 = applicationContext.getBean("userServiceV10", UserServiceV10.class);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(userServiceV10_1);
        System.out.println(userServiceV10_2);

        // 4. 打印十六进制哈希
        System.out.println(userServiceV10_1 + " 十六进制哈希：" + Integer.toHexString(userServiceV10_1.hashCode()));
        //System.out.println(ClassLayout.parseInstance(userServiceV10_1).toPrintable());
    }


    @Test
    public void test_factory_bean(){
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        UserServiceV10 userServiceV10 = applicationContext.getBean("userServiceV10", UserServiceV10.class);
        System.out.println(userServiceV10.queryUserNameById(1L));
    }

    @Test
    public void test_event(){
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 110L, "自定义事件通知！"));
        applicationContext.registerShutdownHook();
    }

    @Test
    public void test_proxy_class(){
        IUserDao iUserDao = (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, (proxy, method, args) -> "代理类执行!");
        String userName = iUserDao.queryUserName(1L);
        System.out.println(userName);
    }


    @Test
    public void test_scan(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
        IGoodsService goodsService = applicationContext.getBean("goodsService", IGoodsService.class);
        System.out.println(goodsService.getUsername(1L));
    }
}
