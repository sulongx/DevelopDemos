package com.sulongx.patterns.observerpattern;

/**
 * 描述:
 *
 * @author xiongsulong
 * @create 2020-10-28 22:48
 */
public class ObserverPatternTest {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();

        ConcreteObserver1 observer1 = new ConcreteObserver1();
        ConcreteObserver2 observer2 = new ConcreteObserver2();
        subject.add(observer1);
        subject.add(observer2);
        //被观察者通知观察者
        subject.notifyObserver();
    }
}
