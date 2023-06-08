package com.sulongx.springframework.beans.bean;

import java.util.List;

/**
 * @author sulongx
 * @title 商品服务接口
 * @details
 * @date 2022/12/11
 */
public interface IGoodsService {

    List<String> getGoods();

    String getToken();

    String getUsername(Long id);
}
