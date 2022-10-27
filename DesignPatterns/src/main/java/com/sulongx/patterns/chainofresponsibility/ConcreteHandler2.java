package com.sulongx.patterns.chainofresponsibility;

/**
 * @author sulongx
 * @title 具体处理角色2
 * @details
 * @date 2022/6/6
 */
public class ConcreteHandler2 extends Handler{
    @Override
    public void handleRequest(String request) {
        if("two".equals(request)){
            System.out.println("具体处理者2负责处理该请求!");
        }else {
            if(getNext() != null){
                getNext().handleRequest(request);
            }else {
                System.err.println("没有人处理该请求!");
            }
        }
    }
}
