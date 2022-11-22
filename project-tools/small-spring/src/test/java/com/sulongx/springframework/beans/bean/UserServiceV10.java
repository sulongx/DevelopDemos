package com.sulongx.springframework.beans.bean;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2022/11/22 15:18
 **/
public class UserServiceV10 {

    private IUserDao iUserDao;


    public String queryUserNameById(Long userId){
        return iUserDao.queryUserName(userId);
    }
}
