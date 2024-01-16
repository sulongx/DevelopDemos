package com.sulongx.patterns.decoratorpattern;

/**
 * 描述:
 * 具体装饰角色
 *
 * @author xiongsulong
 * @create 2020-10-28 19:29
 */
public class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation(){
        super.operation();
        addedFunction();
    }

    private void addedFunction(){
        System.out.println("为具体构件角色添加额外的功能addedFunction()");
    }
}
