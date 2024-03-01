package com.sulongx.tools.mybatis.session.defaults;

import com.sulongx.tools.mybatis.binding.MapperRegistry;
import com.sulongx.tools.mybatis.session.SqlSession;
import com.sulongx.tools.mybatis.session.SqlSessionFactory;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2024/3/1 16:54
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}

