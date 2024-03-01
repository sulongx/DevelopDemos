package com.sulongx.tools.mybatis.binding;

import com.sulongx.tools.mybatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author sulongx
 * @version 1.0
 * @description 映射器代理
 * @date 2024/3/1 14:32
 **/
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = 1L;


    private SqlSession sqlSession;
    private final Class<T> mapperInterface;


    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            return sqlSession.selectOne(mapperInterface.getName(), args);
        }
    }

}
