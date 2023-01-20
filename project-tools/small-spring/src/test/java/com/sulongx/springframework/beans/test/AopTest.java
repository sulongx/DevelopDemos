package com.sulongx.springframework.beans.test;

import com.sulongx.springframework.aop.AdvisedSupport;
import com.sulongx.springframework.aop.TargetSource;
import com.sulongx.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.sulongx.springframework.aop.framework.Cglib2AopProxy;
import com.sulongx.springframework.aop.framework.JdkDynamicAopProxy;
import com.sulongx.springframework.beans.aop.GoodsServiceInterceptor;
import com.sulongx.springframework.beans.bean.GoodsService;
import com.sulongx.springframework.beans.bean.IGoodsService;
import com.sulongx.springframework.beans.bean.UserService;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2022/12/3
 */
public class AopTest {



    @Test
    public void test_aop_matches() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.sulongx.springframework.beans.bean.UserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("getInfo");
        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }

    @Test
    public void test_aop_dynamic(){
        //目标对象
        IGoodsService goodsService = new GoodsService();

        //组织代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(goodsService));
        advisedSupport.setMethodInterceptor(new GoodsServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.sulongx.springframework.beans.bean.IGoodsService.*(..))"));

        //JDK动态代理方式-代理对象
        IGoodsService proxyGoodsService = (IGoodsService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        //执行
        System.out.println("JDK动态代理方式执行结果: " + proxyGoodsService.getGoods());


        //Cglib动态代理方式-代理对象
        IGoodsService cglibGoodsService = (IGoodsService) new Cglib2AopProxy(advisedSupport).getProxy();
        //执行
        System.out.println("Cglib动态代理方式结果: " + cglibGoodsService.getGoods());
    }
}
