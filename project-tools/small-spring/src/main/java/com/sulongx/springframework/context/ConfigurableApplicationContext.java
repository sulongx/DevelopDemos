package com.sulongx.springframework.context;

import com.sulongx.springframework.beans.exception.BeansException;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2022/10/31 14:49
 **/
public interface ConfigurableApplicationContext extends ApplicationContext{

    void refresh() throws BeansException;

}
