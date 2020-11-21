package com.sulongx.patterns.templatemethodpattern;

/**
 * 描述:
 * 具体子类
 *
 * @author xiongsulong
 * @create 2020-10-29 22:15
 */
public class ConcreteClass extends AbstractClass{

    @Override
    public void abstractMethod1() {
        System.out.println("抽象方法1的具体调用...");
    }

    @Override
    public void abstractMethod2() {
        System.out.println("抽象方法2的具体调用...");
    }
}
