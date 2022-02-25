package com.sulongx.jvm.compile;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author sulongx
 * @version 1.0
 * @description TODO
 * @date 2021/12/27 9:11
 **/
public class StringDemo {

    public static void main(String[] args) {
        test1();
        test2();
    }

    public static void test1(){
        String str = "";
        for (int i = 0; i < 10; i++) {
            str += i;
        }
        System.out.println(str);
    }

    public static void test2(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            builder.append(i);
        }
        System.out.println(builder);
    }
}
