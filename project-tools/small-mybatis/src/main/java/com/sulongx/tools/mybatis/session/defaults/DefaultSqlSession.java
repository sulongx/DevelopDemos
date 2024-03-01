package com.sulongx.tools.mybatis.session.defaults;

import com.sulongx.tools.mybatis.binding.MapperRegistry;
import com.sulongx.tools.mybatis.session.SqlSession;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2024/3/1 16:51
 **/
public class DefaultSqlSession implements SqlSession {

    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了！" + "方法：" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("你被代理了！" + "方法：" + statement + " 入参：" + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }
}
