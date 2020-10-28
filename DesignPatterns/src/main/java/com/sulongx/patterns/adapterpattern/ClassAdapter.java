package com.sulongx.patterns.adapterpattern;

/**
 * 描述:
 * 类适配器
 *
 * @author xiongsulong
 * @create 2020-10-28 19:06
 */
public class ClassAdapter extends Adaptee implements Target {

    @Override
    public void request() {
        specificRequest();
    }
}
