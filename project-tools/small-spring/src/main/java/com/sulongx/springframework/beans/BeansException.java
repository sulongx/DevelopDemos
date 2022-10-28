package com.sulongx.springframework.beans;

/**
 * @author sulongx
 * @version 1.0
 * @description bean异常类
 * @date 2022/10/28 16:42
 **/
public class BeansException extends RuntimeException {


    public BeansException(String message){
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }

}
