package com.sulongx.jvm.gc;

/**
 * @author sulongx
 * @version 1.0
 * @description TODO
 * @date 2021/11/3 10:46
 **/
public class Main {

    public static void main(String[] args) {
        new Thread(new ModelAllocator()).start();
    }
}
