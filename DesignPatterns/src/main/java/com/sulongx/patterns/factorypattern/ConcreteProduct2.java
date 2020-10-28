package com.sulongx.patterns.factorypattern;

/**
 * 描述:
 * 具体产品二显示
 *
 * @author xiongsulong
 * @create 2020-10-27 21:44
 */
public class ConcreteProduct2 implements Product{
    @Override
    public void show() {
        System.out.println("具体产品二显示");
    }
}
