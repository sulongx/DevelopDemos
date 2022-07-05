package com.sulongx.patterns.bridgepattern;

/**
 * @author sulongx
 * @version 1.0
 * @description 扩展抽象化角色
 * @date 2022/7/1 15:57
 **/
public class RefinedAbstraction extends Abstraction{

    protected RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void Operation() {
        System.out.println("扩展抽象化(" + this.getClass().getName() + ")角色被访问");
        implementor.OperationImpl();
    }
}
