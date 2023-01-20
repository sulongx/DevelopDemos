package com.sulongx.springframework.beans.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2022/12/11
 */
public class GoodsService implements IGoodsService{

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
}
