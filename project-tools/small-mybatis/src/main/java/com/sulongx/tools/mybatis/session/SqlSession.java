package com.sulongx.tools.mybatis.session;

/**
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2024/3/1 16:22
 **/
public interface SqlSession {

    /**
     * 根据指定的sqlId获取一条记录的封装对象
     * @param statement statement ID
     * @param <T> 封装之后的对象类型
     * @return 封装之后的对象
     */
    <T> T selectOne(String statement);

    /**
     * 根据指定的sqlId获取一条记录的封装对象，并传递一些参数
     * @param statement statement ID
     * @param parameter 传递的参数
     * @param <T> 封装之后的对象类型
     * @return 封装之后的对象
     */
    <T> T selectOne(String statement, Object parameter);

    /**
     * 得到映射器
     * @param type 映射器类型
     * @param <T> 映射器接口的class
     * @return 映射器
     */
    <T> T getMapper(Class<T> type);
}
