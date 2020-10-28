package com.sulongx.patterns.decoratorpattern;

/**
 * 描述:
 * 抽象装饰角色
 *
 * @author xiongsulong
 * @create 2020-10-28 19:27
 */
public abstract class Decorator implements Component{

    private Component component;

    public Decorator(Component component){
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}
