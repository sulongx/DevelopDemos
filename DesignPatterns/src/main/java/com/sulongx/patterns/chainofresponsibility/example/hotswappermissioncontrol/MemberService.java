package com.sulongx.patterns.chainofresponsibility.example.hotswappermissioncontrol;

/**
 * @author sulongx
 * @title 会员服务-责任链模式实现登录逻辑
 * @details
 * @date 2022/6/6
 */
public class MemberService {

    public static void main(String[] args) {
        MemberService memberService = new MemberService();
        memberService.login("张三", "123456");
    }


    public void login(String username, String password){
        Handler validateHandler = new ValidateHandler();
        Handler loginHandler = new LoginHandler();
        Handler authHandler = new AuthHandler();

        validateHandler.next(loginHandler);
        loginHandler.next(authHandler);

        validateHandler.doHandle(new Member(username, password));
    }
}
