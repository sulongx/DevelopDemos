package com.sulongx.patterns.observerpatterns;

/**
 * 描述:
 * 具体观察者2
 *
 * @author xiongsulong
 * @create 2020-10-28 22:47
 */
public class ConcreteObserver2 implements Observer {

    @Override
    public void response() {
        System.out.println("具体观察者2做出反应!");
    }
}
