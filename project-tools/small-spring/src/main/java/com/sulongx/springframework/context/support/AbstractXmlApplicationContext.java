package com.sulongx.springframework.context.support;

import com.sulongx.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.sulongx.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2022/10/31 15:51
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if(configLocations != null){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }


    protected abstract String[] getConfigLocations();
}
