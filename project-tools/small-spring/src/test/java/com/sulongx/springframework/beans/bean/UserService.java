package com.sulongx.springframework.beans.bean;

/**
 * @author sulongx
 * @version 1.0
 * @description 用户服务Bean类
 * @date 2022/10/28 17:54
 **/
public class UserService {

    private String name;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void getInfo(){
        System.out.println("获取用户信息:");
        System.out.println(toString());
    }


    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                '}';
    }
}
