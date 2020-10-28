package com.sulongx.patterns.singletonpattern;

/**
 * 描述:
 * 饿汉式单例
 *
 * @author xiongsulong
 * @create 2020-10-28 18:58
 */
public class HungrySingleton {

    private static final HungrySingleton singleton = new HungrySingleton();
    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return singleton;
    }
}
