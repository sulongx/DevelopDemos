package com.sulongx.patterns.chainofresponsibility;

/**
 * @author sulongx
 * @title 抽象处理者角色
 * @details
 * @date 2022/6/6
 */
public abstract class Handler {
    private Handler next;

    public void setNext(Handler next){
        this.next = next;
    }

    public Handler getNext() {
        return next;
    }

    //处理请求的方法
    public abstract void handleRequest(String request);
}
