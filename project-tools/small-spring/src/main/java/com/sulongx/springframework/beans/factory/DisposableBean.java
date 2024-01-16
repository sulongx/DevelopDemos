package com.sulongx.springframework.beans.factory;

/**
 * @author sulongx
 * @version 1.0
 * @description Bean销毁后的执行
 * @date 2022/11/16 11:02
 **/
public interface DisposableBean {

    void destroy() throws Exception;
}
