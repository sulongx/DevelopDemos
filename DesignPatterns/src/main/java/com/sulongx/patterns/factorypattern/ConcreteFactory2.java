package com.sulongx.patterns.factorypattern;

/**
 * 描述:
 * 具体工厂二
 *
 * @author xiongsulong
 * @create 2020-10-27 21:48
 */
public class ConcreteFactory2 implements AbstractFactory {
    @Override
    public Product newProduct() {
        System.out.println("具体工厂二生产-->具体产品二\n");
        return new ConcreteProduct2();
    }
}
