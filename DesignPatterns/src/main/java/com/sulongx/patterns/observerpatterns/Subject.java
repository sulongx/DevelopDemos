package com.sulongx.patterns.observerpatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 抽象目标
 *
 * @author xiongsulong
 * @create 2020-10-28 22:32
 */
public abstract class Subject {
    protected List<Observer> observers = new ArrayList<>();

    /**
     * 增加观察者方法
     * @param observer
     */
    public void add(Observer observer){
        observers.add(observer);
    }

    /**
     * 删除观察者方法
     * @param observer
     */
    public void remove(Observer observer){
        observers.remove(observer);
    }

    /**
     * 通知观察者方法
     */
    public abstract void notifyObserver();


}
