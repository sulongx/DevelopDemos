package com.sulongx.patterns.singletonpattern;

/**
 * 描述:
 * 懒汉式单例
 *
 * @author xiongsulong
 * @create 2020-10-28 18:54
 */
public class LazySingleton {

    private static volatile LazySingleton singleton = null;
    private LazySingleton(){}

    public static LazySingleton getInstance(){
        if(singleton == null){
            synchronized (singleton) {
                if(singleton == null){
                    singleton = new LazySingleton();
                }
            }
        }
        return singleton;
    }
}
