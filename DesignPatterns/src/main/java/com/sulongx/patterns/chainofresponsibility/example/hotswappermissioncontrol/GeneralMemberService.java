package com.sulongx.patterns.chainofresponsibility.example.hotswappermissioncontrol;

import org.apache.commons.lang3.StringUtils;

/**
 * @author sulongx
 * @title 会员服务处理(一般处理方式 )
 * @details
 * @date 2022/6/6
 */
public class GeneralMemberService {

    public static void main(String[] args) {
        GeneralMemberService generalMemberService = new GeneralMemberService();
        generalMemberService.login("张三", "123456");
    }

    public void login(String username, String password){
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            System.err.println("用户名或密码不能为空");
            return;
        }
        System.out.println("登录成功");
        System.out.println("用户名: " + username + ",密码: " + password);
        Member member = checkExist(username, password);
        if(member == null){
            System.err.println("用户不存在");
            return;
        }
        if(!"管理员".equals(member.getRoleName())){
            System.err.println("您不是管理员,没有权限操作!");
            return;
        }
        System.out.println("操作允许");
    }

    private Member checkExist(String username, String password){
        Member member = new Member(username, password);
        member.setRoleName("管理员");
        return member;
    }
}
