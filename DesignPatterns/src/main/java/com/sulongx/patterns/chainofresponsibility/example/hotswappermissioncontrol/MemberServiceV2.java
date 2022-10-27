package com.sulongx.patterns.chainofresponsibility.example.hotswappermissioncontrol;

/**
 * @author sulongx
 * @title 会员服务-责任链模式实现登录逻辑-配合建造者模式
 * @details
 * @date 2022/6/6
 */
public class MemberServiceV2 {

    public static void main(String[] args) {
        MemberServiceV2 memberServiceV2 = new MemberServiceV2();

        memberServiceV2.login("admin", "123456");
    }


    public void login(String username, String password){
        HandlerV2.Builder build = new HandlerV2.Builder<>();

        build.addHandler(new ValidateHandlerV2())
                .addHandler(new LoginHandlerV2())
                .addHandler(new AuthHandlerV2());

        build.build().doHandle(new Member(username, password));
    }
}
