package com.sulongx.tools.mybatis.binding;

import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author sulongx
 * @version 1.0
 * @description 代理工厂
 * @date 2024/3/1 14:40
 **/
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(Map<String, Object> sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
