package com.sulongx.springframework.context.event;

import com.sulongx.springframework.context.ApplicationEvent;
import com.sulongx.springframework.context.ApplicationListener;

/**
 * @author sulongx
 * @title 事件广播器
 * @details
 * @date 2022/11/23
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);


    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}
