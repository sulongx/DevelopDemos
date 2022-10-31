package com.sulongx.springframework.context.support;

import com.sulongx.springframework.beans.exception.BeansException;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2022/10/31 15:58
 **/
public class ClassPathXmlApplicationContext  extends AbstractXmlApplicationContext{

    private String[] configLocations;

    public ClassPathXmlApplicationContext() {
    }

    public ClassPathXmlApplicationContext(String configLocations) throws BeansException{
        this(new String[]{configLocations});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
