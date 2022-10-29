package com.sulongx.springframework.beans.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sulongx
 * @title 用户数据层
 * @details
 * @date 2022/10/29
 */
public class UserDao {

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
}
