package com.sulongx.jvm.jit.monomorphicdispatch;

/**
 * @author sulongx
 * @version 1.0
 * @description 正方形
 * @date 2022/4/25 11:36
 **/
public class Square implements Shape{

    @Override
    public int getSides() {
        return 4;
    }
}
