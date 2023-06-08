package com.sulongx.springframework.beans.bean;

import com.sulongx.springframework.beans.factory.annotation.Autowired;
import com.sulongx.springframework.beans.factory.annotation.Value;
import com.sulongx.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2022/12/11
 */
@Component("goodsService")
public class GoodsService implements IGoodsService{

    @Value("${token}")
    private String token;

    @Autowired
    private IUserDao userDao;
    private static List<String> goods = new ArrayList<>();

    static {
        goods.add("羽绒服");
        goods.add("篮球");
        goods.add("手机");
        goods.add("耳机");
    }

    @Override
    public List<String> getGoods() {
        return goods;
    }

    @Override
    public String getUsername(Long id) {
        return userDao.queryUserName(id) + ", token=" + token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
