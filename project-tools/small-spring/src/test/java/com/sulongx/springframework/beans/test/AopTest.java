package com.sulongx.springframework.beans.test;

import com.sulongx.springframework.aop.AdvisedSupport;
import com.sulongx.springframework.aop.MethodMatcher;
import com.sulongx.springframework.aop.TargetSource;
import com.sulongx.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.sulongx.springframework.aop.framework.Cglib2AopProxy;
import com.sulongx.springframework.aop.framework.JdkDynamicAopProxy;
import com.sulongx.springframework.aop.framework.ReflectiveMethodInvocation;
import com.sulongx.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import com.sulongx.springframework.beans.aop.GoodsServiceInterceptor;
import com.sulongx.springframework.beans.aop.bean.IUserService;
import com.sulongx.springframework.beans.bean.GoodsService;
import com.sulongx.springframework.beans.bean.IGoodsService;
import com.sulongx.springframework.beans.bean.UserService;
import com.sulongx.springframework.context.support.ClassPathXmlApplicationContext;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

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


    @Test
    public void test_proxy_method(){
        //目标对象
        Object targetObj = new GoodsService();

        //AOP代理
        IGoodsService iGoodsService = (IGoodsService)Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {

            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.sulongx.springframework.beans.bean.IGoodsService.*(..))");

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(methodMatcher.matches(method, targetObj.getClass())){

                    MethodInterceptor methodInterceptor = invocation -> {

                        long startTime = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        }finally {
                            System.out.println("监控 - Begin by AOP");
                            System.out.println("方法名称 - " + method.getName());
                            System.out.printf("耗时%d ms", System.currentTimeMillis() - startTime);
                        }
                    };

                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
                }
                return method.invoke(targetObj, args);
            }
        });
        List<String> goods = iGoodsService.getGoods();
        System.out.println("测试结果: " + goods);
    }

    @Test
    public void test_aop(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果: " + userService.queryUserInfo());
    }
    @Test
    public void test_property(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-property.xml");
        IGoodsService goodsService = applicationContext.getBean("goodsService", IGoodsService.class);
        System.out.println(goodsService.getToken());
    }

    @Test
    public void test_scan(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
        IGoodsService goodsService = applicationContext.getBean("goodsService", IGoodsService.class);
        System.out.println(goodsService.getGoods());
    }
}
