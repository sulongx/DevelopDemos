package com.sulongx.patterns.decoratorpattern;

/**
 * 描述:
 * 具体构件角色
 *
 * @author xiongsulong
 * @create 2020-10-28 19:25
 */
public class ConcreteComponent implements Component {

    public ConcreteComponent(){
        System.out.println("创建具体构件角色");
    }

    @Override
    public void operation() {
        System.out.println("调用具体构件角色的operation()方法");
    }
}
