package com.sulongx.patterns.adapterpattern;

/**
 * 描述:
 * 对象适配器
 *
 * @author xiongsulong
 * @create 2020-10-28 19:11
 */
public class ObjectAdapter implements Target{

    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee){
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}
