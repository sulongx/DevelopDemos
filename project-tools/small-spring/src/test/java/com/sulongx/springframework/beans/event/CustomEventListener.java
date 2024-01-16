package com.sulongx.springframework.beans.event;

import com.sulongx.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2022/11/24
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getApplicationContext() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
