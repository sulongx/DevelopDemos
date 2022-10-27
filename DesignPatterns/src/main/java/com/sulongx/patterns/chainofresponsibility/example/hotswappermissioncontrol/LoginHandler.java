package com.sulongx.patterns.chainofresponsibility.example.hotswappermissioncontrol;

/**
 * @author sulongx
 * @title 登录处理handler
 * @details
 * @date 2022/6/6
 */
public class LoginHandler extends Handler{
    @Override
    public void doHandle(Member member) {
        System.out.println("登陆成功");
        if("admin".equals(member.getUsername())){
            member.setRoleName("管理员");
        }else {
            member.setRoleName("普通用户");
        }
        chain.doHandle(member);
    }
}
