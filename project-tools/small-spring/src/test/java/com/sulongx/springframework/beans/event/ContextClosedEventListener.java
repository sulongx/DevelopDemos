package com.sulongx.springframework.beans.event;

import com.sulongx.springframework.context.ApplicationListener;
import com.sulongx.springframework.context.event.ContextClosedEvent;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2022/11/24
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + event.getSource());
    }
}
