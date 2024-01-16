package com.sulongx.springframework.beans.event;

import com.sulongx.springframework.context.ApplicationContext;
import com.sulongx.springframework.context.event.ApplicationContextEvent;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2022/11/24
 */
public class CustomEvent extends ApplicationContextEvent {

    private Long id;

    private String message;

    public CustomEvent(ApplicationContext source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
