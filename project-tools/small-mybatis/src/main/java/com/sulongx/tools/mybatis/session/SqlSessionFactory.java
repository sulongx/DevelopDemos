package com.sulongx.tools.mybatis.session;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2024/3/1 16:54
 **/
public interface SqlSessionFactory {

    /**
     * 打开一个session
     * @return
     */
    SqlSession openSession();
}
