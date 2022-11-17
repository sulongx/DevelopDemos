package com.sulongx.springframework.beans.factory;

/**
 * @author sulongx
 * @version 1.0
 * @description Bean初始化的执行
 * @date 2022/11/16 11:00
 **/
public interface InitializingBean {

    /**
     * Bean 处理了属性填充后调用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;

}
