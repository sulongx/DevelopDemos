package com.sulongx.patterns.factorypattern;

/**
 * 描述:
 *  具体产品一
 * @author xiongsulong
 * @create 2020-10-27 21:43
 */
public class ConcreteProduct1 implements Product{
    @Override
    public void show() {
        System.out.println("具体产品一-->显示");
    }
}
