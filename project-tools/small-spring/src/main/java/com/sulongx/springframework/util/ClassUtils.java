package com.sulongx.springframework.util;

/**
 * @author sulongx
 * @title 类工具
 * @details
 * @date 2022/10/30
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader(){
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        }catch (Throwable ex){

        }
        if(cl == null){
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }
}
