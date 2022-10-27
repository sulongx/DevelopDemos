package com.sulongx.patterns.chainofresponsibility.example.hotswappermissioncontrol;

/**
 * @author sulongx
 * @title 权限处理Handler-配合建造者模式
 * @details
 * @date 2022/6/6
 */
public class AuthHandlerV2 extends HandlerV2{
    @Override
    public void doHandle(Member member) {
        if(!"管理员".equals(member.getRoleName())){
            System.err.println("您不是管理员,没有操作权限!");
            return;
        }
        System.out.println("您是管理员,允许操作!");
    }
}
