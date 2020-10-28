package com.sulongx.patterns.proxypattern.dynamicproxypatterns;

/**
 * 描述:
 * 需要被代理的类
 *
 * @author xiongsulong
 * @create 2020-10-28 21:05
 */
public class Student implements Person{

    private String name;

    public Student(String name){
        this.name = name;
    }

    @Override
    public void giveMoney() {
        System.out.println(name + "交学费...\n");
    }

    @Override
    public void study() {
        System.out.println(name + "正在学习...\n");
    }
}
