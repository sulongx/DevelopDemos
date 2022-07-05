package com.sulongx.patterns.bridgepattern;

/**
 * @author sulongx
 * @version 1.0
 * @description 抽象化角色
 * @date 2022/7/1 15:53
 **/
public abstract class Abstraction {

    protected Implementor implementor;

    protected Abstraction(Implementor implementor){
        this.implementor = implementor;
    }

    public abstract void Operation();
}
