package com.sulongx.springframework.beans.test;

import cn.hutool.core.io.IoUtil;
import com.sulongx.springframework.beans.bean.UserService;
import com.sulongx.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.sulongx.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.sulongx.springframework.core.io.DefaultResourceLoader;
import com.sulongx.springframework.core.io.FileSystemResource;
import com.sulongx.springframework.core.io.Resource;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author sulongx
 * @title xml加载测试
 * @details
 * @date 2022/10/30
 */
public class XmlBeanLoaderTest {

    private DefaultResourceLoader resourceLoader;



    @Before
    public void init(){
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classpath() throws IOException{
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException{
        Resource resource = resourceLoader.getResource("src/test/resource/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_url() throws IOException{
        Resource resource = resourceLoader.getResource("https://github.com/sulongx/small-spring/blob/0e24960bd6d06bc35b2ad256353b949108f6151d/small-spring-step-05/src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_xml(){
        //初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //读取配置文件 注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        //获取Bean对象并调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        System.out.println(userService.getUserName("2"));;
    }
}
