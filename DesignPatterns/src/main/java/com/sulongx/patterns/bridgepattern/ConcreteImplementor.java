package com.sulongx.patterns.bridgepattern;

/**
 * @author sulongx
 * @version 1.0
 * @description 具体实现化角色
 * @date 2022/7/1 16:00
 **/
public class ConcreteImplementor implements Implementor{

    @Override
    public void OperationImpl() {
        System.out.println("具体实现化(" + this.getClass().getName() + ")角色被访问");
    }
}
