package com.sulongx.patterns.chainofresponsibility.example.hotswappermissioncontrol;

/**
 * @author sulongx
 * @title 权限处理Handler
 * @details
 * @date 2022/6/6
 */
public class AuthHandler extends Handler{
    @Override
    public void doHandle(Member member) {
        if(!"管理员".equals(member.getRoleName())){
            System.err.println("您不是管理员,没有操作权限!");
            return;
        }
        System.out.println("您是管理员,允许操作!");
    }
}
