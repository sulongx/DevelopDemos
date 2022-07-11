package com.sulongx.patterns.facade;

/**
 * @author sulongx
 * @title 外观角色
 * @details
 * @date 2022/7/11
 */
public class Facade {
    private SubSystem01 obj1 = new SubSystem01();
    private SubSystem02 obj2 = new SubSystem02();
    private SubSystem03 obj3 = new SubSystem03();

    public void method() {
        obj1.method01();
        obj2.method02();
        obj3.method03();
    }
}
