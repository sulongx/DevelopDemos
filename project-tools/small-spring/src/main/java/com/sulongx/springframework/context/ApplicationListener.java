package com.sulongx.springframework.context;

import java.util.EventListener;

/**
 * @author sulongx
 * @title Application监听者接口
 * @details
 * @date 2022/11/23
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);
}
