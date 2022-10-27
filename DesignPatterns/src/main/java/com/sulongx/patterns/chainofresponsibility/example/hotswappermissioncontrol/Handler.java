package com.sulongx.patterns.chainofresponsibility.example.hotswappermissioncontrol;

/**
 * @author sulongx
 * @title 抽象处理
 * @details
 * @date 2022/6/6
 */
public abstract class Handler {
    protected Handler chain;

    public void next(Handler chain) {
        this.chain = chain;
    }

    public abstract void doHandle(Member member);
}
