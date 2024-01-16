package com.sulongx.springframework.aop.aspectj;

import com.sulongx.springframework.aop.Pointcut;
import com.sulongx.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @author sulongx
 * @title
 * @details
 * @date 2023/2/19
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {


    //切面
    private AspectJExpressionPointcut pointcut;

    //拦截方法
    private Advice advice;

    //表达式
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if(pointcut == null){
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
