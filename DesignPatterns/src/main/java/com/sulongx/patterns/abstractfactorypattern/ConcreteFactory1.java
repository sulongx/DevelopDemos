package com.sulongx.patterns.abstractfactorypattern;

/**
 * 描述:
 * 具体工厂
 *
 * @author xiongsulong
 * @create 2020-10-28 18:49
 */
public class ConcreteFactory1 implements AbstractFactory{
    @Override
    public Product1 newProduct1() {
        System.out.println("具体工厂1 生成--> 具体产品 1...");
        return new ConcreteProduct1();
    }

    @Override
    public Product2 newProduct2() {
        System.out.println("具体工厂2 生成--> 具体产品 2...");
        return new ConcreteProduct2();
    }
}
