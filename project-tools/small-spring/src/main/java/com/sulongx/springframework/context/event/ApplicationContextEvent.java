package com.sulongx.springframework.context.event;

import com.sulongx.springframework.context.ApplicationContext;
import com.sulongx.springframework.context.ApplicationEvent;

/**
 * @author sulongx
 * @title 定义Application上下文事件抽象类
 * @details
 * @date 2022/11/23
 */
public class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) getSource();
    }
}
