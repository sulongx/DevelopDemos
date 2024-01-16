package com.sulongx.springframework.beans.bean;

import com.sulongx.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sulongx
 * @title 用户数据层
 * @details
 * @date 2022/10/29
 */
@Component()
public class UserDao implements IUserDao{

    private static Map<String, String> data = new HashMap<>();

    static {
        data.put("1", "张三");
        data.put("2", "李四");
        data.put("3", "王二");
        data.put("4", "麻子");
    }

    public String queryUserName(String id){
        return data.get(id);
    }


    public void initMethod(){
        System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ":执行初始化方法...[配置文件方式]");
    }

    public void destroyMethod(){
        System.out.println(this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName() + ":执行销毁方法...[配置文件方式]");
    }

    @Override
    public String queryUserName(Long userId) {
        return data.get(userId.toString());
    }
}
