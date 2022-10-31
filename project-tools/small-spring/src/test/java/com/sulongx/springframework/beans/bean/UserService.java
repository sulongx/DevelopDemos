package com.sulongx.springframework.beans.bean;

/**
 * @author sulongx
 * @version 1.0
 * @description 用户服务Bean类
 * @date 2022/10/28 17:54
 **/
public class UserService {

    private String name;

    private String msg;

    private UserDao userDao;

    public UserService() {
    }



    public void getInfo(){
        System.out.println("获取用户信息:");
        System.out.println(toString());
    }


    public String getUserName(String id){
        return userDao.queryUserName(id);
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
