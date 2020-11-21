package com.sulongx.patterns.adapterpattern;

/**
 * 描述:
 * 对象适配器测试
 *
 * @author xiongsulong
 * @create 2020-10-28 19:12
 */
public class ObjectAdapterTest {
    public static void main(String[] args) {
        System.out.println("对象适配器测试:\n");
        Adapter adapter = new Adapter();
        Target target = new ObjectAdapter(adapter);
        target.request();
    }
}
