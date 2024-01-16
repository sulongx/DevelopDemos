package com.sulongx.patterns.chainofresponsibility.example.hotswappermissioncontrol;

import org.apache.commons.lang3.StringUtils;

/**
 * @author sulongx
 * @title 输入验证处理handler-配合建造者模式
 * @details
 * @date 2022/6/6
 */
public class ValidateHandlerV2 extends HandlerV2{

    @Override
    public void doHandle(Member member) {
        if (StringUtils.isEmpty(member.getUsername()) ||
                StringUtils.isEmpty(member.getPassword())) {
            System.out.println("用户名和密码为空");
            return;
        }
        System.out.println("用户名和密码不为空，可以往下执行");
        System.out.println("用户名：" + member.getUsername() + ",密码：" + member.getPassword());
        chain.doHandle(member);
    }
}
