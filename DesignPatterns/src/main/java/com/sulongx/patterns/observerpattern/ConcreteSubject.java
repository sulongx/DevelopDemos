package com.sulongx.patterns.observerpattern;

/**
 * 描述:
 * 具体目标
 *
 * @author xiongsulong
 * @create 2020-10-28 22:43
 */
public class ConcreteSubject extends Subject{

    @Override
    public void notifyObserver() {
        System.out.println("具体目标发生改变...\n");
        for(Observer obs : observers){
            obs.response();
        }
    }
}
