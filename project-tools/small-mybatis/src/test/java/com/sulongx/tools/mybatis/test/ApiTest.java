package com.sulongx.tools.mybatis.test;

import com.sulongx.tools.mybatis.binding.MapperProxyFactory;
import com.sulongx.tools.mybatis.binding.MapperRegistry;
import com.sulongx.tools.mybatis.session.SqlSession;
import com.sulongx.tools.mybatis.session.defaults.DefaultSqlSessionFactory;
import com.sulongx.tools.mybatis.test.dao.IUserDao;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sulongx
 * @version 1.0
 * @description 测试
 * @date 2024/3/1 14:46
 **/
public class ApiTest {

    @Test
    public void test_MapperProxyFactory() {
        //注册mapper
        MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.addMappers("com.sulongx.tools.mybatis.test.dao");

        //从session工厂中获取session
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(mapperRegistry);
        SqlSession sqlSession = defaultSqlSessionFactory.openSession();

        //获取映射器对象
        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);

        //测试验证
        System.out.println(iUserDao.queryUserName("1L"));
    }
}
