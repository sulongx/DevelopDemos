package com.sulongx.patterns.chainofresponsibility.example.eums;

import java.util.Random;

/**
 * @author sulongx
 * @title 枚举随机类
 * @details
 * @date 2022/6/6
 */
public class Enums {
    private static Random random = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> ec){
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values){
        return values[random.nextInt(values.length)];
    }
}
