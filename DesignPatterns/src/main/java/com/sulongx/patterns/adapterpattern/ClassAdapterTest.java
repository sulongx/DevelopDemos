package com.sulongx.patterns.adapterpattern;

/**
 * 描述:
 * 类适配器测试
 *
 * @author xiongsulong
 * @create 2020-10-28 19:09
 */
public class ClassAdapterTest {
    public static void main(String[] args) {
        System.out.println("类适配器测试:\n");
        Target target = new ClassAdapter();
        target.request();
    }
}
