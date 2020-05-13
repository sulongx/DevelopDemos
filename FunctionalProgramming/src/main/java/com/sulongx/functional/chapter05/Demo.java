package com.sulongx.functional.chapter05;

import java.util.ArrayList;

/**
 * @author Sulongx
 * @version 1.0
 * @date 2020-04-27
 */
public class Demo {

    public static void main(String[] args) {
        new ArrayList<>().stream().unordered().filter(item -> {
            return true;
        });
    }
}
