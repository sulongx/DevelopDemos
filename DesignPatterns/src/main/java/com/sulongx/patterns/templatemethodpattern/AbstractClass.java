package com.sulongx.patterns.templatemethodpattern;

/**
 * 描述:
 * 抽象类
 *
 * @author xiongsulong
 * @create 2020-10-29 22:07
 */
public abstract class AbstractClass {

    /**
     * 模板方法
     */
    public void templateMethod(){
        specificMethod();
        abstractMethod1();
        abstractMethod2();
    }


    /**
     * 具体方法
     */
    public void specificMethod(){
        System.out.println("抽象类中的具体方法被调用...");
    }

    /**
     * 抽象方法1
     */
    public abstract void abstractMethod1();

    /**
     * 抽象方法2
     */
    public abstract void abstractMethod2();

}
