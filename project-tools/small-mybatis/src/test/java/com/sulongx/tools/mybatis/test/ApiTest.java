package com.sulongx.tools.mybatis.test;

import com.sulongx.tools.mybatis.binding.MapperProxyFactory;
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
        MapperProxyFactory<IUserDao> mapperProxyFactory = new MapperProxyFactory<>(IUserDao.class);

        Map<String, Object> sqlSession = new HashMap<>();
        sqlSession.put("com.sulongx.tools.mybatis.test.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        sqlSession.put("com.sulongx.tools.mybatis.test.dao.IUserDao.queryUserAge", 18);

        IUserDao iUserDao = mapperProxyFactory.newInstance(sqlSession);
        String userName = iUserDao.queryUserName("1L");
        System.out.println(userName);

        Integer age = iUserDao.queryUserAge("1L");
        System.out.println(age);
    }
}
