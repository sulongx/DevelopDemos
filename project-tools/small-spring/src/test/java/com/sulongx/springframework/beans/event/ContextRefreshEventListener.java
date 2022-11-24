package com.sulongx.springframework.beans.event;

import com.sulongx.springframework.context.ApplicationListener;
import com.sulongx.springframework.context.event.ContextRefreshedEvent;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2022/11/24
 */
public class ContextRefreshEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + event.getApplicationContext());
    }
}
