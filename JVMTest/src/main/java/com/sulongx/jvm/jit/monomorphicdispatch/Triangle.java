package com.sulongx.jvm.jit.monomorphicdispatch;

/**
 * @author sulongx
 * @version 1.0
 * @description 三角形
 * @date 2022/4/25 11:36
 **/
public class Triangle implements Shape{

    @Override
    public int getSides() {
        return 3;
    }
}
