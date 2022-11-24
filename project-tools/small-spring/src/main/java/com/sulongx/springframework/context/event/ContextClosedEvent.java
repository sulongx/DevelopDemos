package com.sulongx.springframework.context.event;

/**
 * @author sulongx
 * @title 监听关闭事件
 * @details
 * @date 2022/11/23
 */
public class ContextClosedEvent extends ApplicationContextEvent{

    public ContextClosedEvent(Object source) {
        super(source);
    }
}
