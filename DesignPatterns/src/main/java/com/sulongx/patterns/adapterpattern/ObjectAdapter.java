package com.sulongx.patterns.adapterpattern;

/**
 * 描述:
 * 对象适配器
 *
 * @author xiongsulong
 * @create 2020-10-28 19:11
 */
public class ObjectAdapter implements Target{

    private Adapter adapter;

    public ObjectAdapter(Adapter adapter){
        this.adapter = adapter;
    }

    @Override
    public void request() {
        adapter.specificRequest();
    }
}
