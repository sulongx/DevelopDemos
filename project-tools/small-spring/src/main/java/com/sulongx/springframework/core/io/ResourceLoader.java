package com.sulongx.springframework.core.io;

/**
 * @author sulongx
 * @title 资源加载
 * @details
 * @date 2022/10/30
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
