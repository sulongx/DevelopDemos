package com.sulongx.patterns.chainofresponsibility.example.hotswappermissioncontrol;

import org.apache.commons.lang3.StringUtils;

/**
 * @author sulongx
 * @title 输入验证处理handler
 * @details
 * @date 2022/6/6
 */
public class ValidateHandler extends Handler{

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
