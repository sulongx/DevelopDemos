package com.sulongx.patterns.chainofresponsibility;

/**
 * @author sulongx
 * @title 责任链模式
 * @details
 * @date 2022/6/6
 */
public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        //组装责任链
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        handler1.setNext(handler2);
        //提交请求
        handler1.handleRequest("three");
    }
}
