package com.sulongx.tools.mybatis.test.dao;

/**
 * @author sulongx
 * @version 1.0
 * @description 测试用户dao
 * @date 2024/3/1 14:44
 **/
public interface IUserDao {
    String queryUserName(String userId);

    Integer queryUserAge(String userId);
}
