package com.sulongx.springframework.context;

import com.sulongx.springframework.beans.exception.BeansException;
import com.sulongx.springframework.beans.factory.Aware;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2022/11/18 9:52
 **/
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext context) throws BeansException;
}
