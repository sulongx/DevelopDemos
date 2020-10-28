package com.sulongx.patterns.factorypattern;

/**
 * 描述:
 * 测试
 *
 * @author xiongsulong
 * @create 2020-10-28 15:22
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        try {
            Product a;
            AbstractFactory af;
            af = (AbstractFactory) ReadXML.getObject();
            a = af.newProduct();
            a.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
