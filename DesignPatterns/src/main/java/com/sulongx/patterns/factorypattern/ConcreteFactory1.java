package com.sulongx.patterns.factorypattern;

/**
 * 描述:
 * 具体工厂一
 *
 * @author xiongsulong
 * @create 2020-10-27 21:46
 */
public class ConcreteFactory1 implements AbstractFactory {
    @Override
    public Product newProduct() {
        System.out.println("具体工厂一生产-->具体产品一\n");
        return new ConcreteProduct1();
    }
}
