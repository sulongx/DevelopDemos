package com.sulongx.patterns.observerpatterns;

/**
 * 描述:
 * 具体观察者1
 *
 * @author xiongsulong
 * @create 2020-10-28 22:46
 */
public class ConcreteObserver1 implements Observer {
    @Override
    public void response() {
        System.out.println("具体观察者1做出反应!\n");
    }
}
