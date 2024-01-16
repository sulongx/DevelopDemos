package com.sulongx.patterns.chainofresponsibility.example.hotswappermissioncontrol;

/**
 * @author sulongx
 * @title 抽象处理-配合建造者模式
 * @details
 * @date 2022/6/6
 */
public abstract class HandlerV2<T> {

    protected HandlerV2 chain;

    private void setNext(HandlerV2 chain) {
        this.chain = chain;
    }

    protected abstract void doHandle(Member member);

    public static class Builder<T> {
        private HandlerV2<T> head;
        private HandlerV2<T> tail;


        public Builder<T> addHandler(HandlerV2<T> handler){
            if(this.head == null){
                this.head = this.tail = handler;
                return this;
            }else if(this.tail == null){
                this.tail = handler;
                this.head.setNext(handler);
                return this;
            }
            this.tail.setNext(handler);
            this.tail = handler;
            return this;
        }

        public HandlerV2<T> build() {
            return this.head;
        }
    }
}
