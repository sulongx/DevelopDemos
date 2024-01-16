package com.sulongx.springframework.beans.factory;

/**
 * @author sulongx
 * @title 属性
 * @details
 * @date 2022/10/29
 */
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
