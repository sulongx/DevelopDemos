package com.sulongx.springframework.context;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2022/11/23
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
