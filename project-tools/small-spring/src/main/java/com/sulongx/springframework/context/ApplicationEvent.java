package com.sulongx.springframework.context;

import java.util.EventObject;

/**
 * @author sulongx
 * @title Application事件接口
 * @details
 * @date 2022/11/23
 */
public abstract class ApplicationEvent extends EventObject {

    public ApplicationEvent(Object source) {
        super(source);
    }
}
