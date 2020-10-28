package com.sulongx.patterns.decoratorpattern;


/**
 * 描述:
 *
 * @author xiongsulong
 * @create 2020-10-28 19:23
 */
public class DecoratorPatternTest {
    public static void main(String[] args) throws Exception{
        Component p = new ConcreteComponent();
        p.operation();
        System.out.println("-----装饰具体构件的方法------\n");

        Component decorator = new ConcreteDecorator(p);
        decorator.operation();
    }
}
