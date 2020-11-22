package com.sulongx;

import java.util.HashMap;

/**
 * 描述:
 * 测试synchronized关键字
 *
 * @author xiongsulong
 * @create 2020-06-17 00:32
 */
public class Demo {

    public synchronized void function01(Object o){
        System.out.printf("获取到管程得以执行代码....");
    }

    public void function02(Object o){

        synchronized(o){
            System.out.println("获取到了管程得以执行代码...");
        }

    }
}
