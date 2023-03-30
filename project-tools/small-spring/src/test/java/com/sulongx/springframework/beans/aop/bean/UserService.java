package com.sulongx.springframework.beans.aop.bean;

import java.util.Random;
public class UserService implements IUserService{

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "测试人员, 100, 杭州";
    }

    @Override
    public String register(String username) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "注册成功, username: " + username;
    }
}
