package com.sulongx.patterns.facade;

/**
 * @author sulongx
 * @title 外观模式
 * @details
 *   又叫门面模式，是一种通过多个复杂的子系统提供一个一致的接口，而使这些子系统更加容易被访问的模式。
 *
 *
 *   缺点：
 *      1. 不能很好地限制客户使用子系统类，很容易带来未知风险。
 *      2. 增加新的子系统可能需要修改外观类或客户端的源码，违背了“开闭原则”。
 * @date 2022/7/11
 */
public class FacadePattern {

    public static void main(String[] args) {
        Facade f = new Facade();
        f.method();
    }

}
