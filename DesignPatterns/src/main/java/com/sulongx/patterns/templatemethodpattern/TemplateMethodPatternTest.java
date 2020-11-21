package com.sulongx.patterns.templatemethodpattern;

/**
 * 描述:
 *
 * @author xiongsulong
 * @create 2020-10-29 22:06
 */
public class TemplateMethodPatternTest {
    public static void main(String[] args) {
        AbstractClass cObject = new ConcreteClass();
        cObject.templateMethod();
    }
}
