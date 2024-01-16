package com.sulongx.springframework.context.event;

/**
 * @author sulongx
 * @title 监听刷新事件
 * @details
 * @date 2022/11/23
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{

    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
