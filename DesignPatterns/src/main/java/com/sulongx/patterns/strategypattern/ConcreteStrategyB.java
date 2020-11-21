package com.sulongx.patterns.strategypattern;

/**
 * 描述:
 * 具体策略B的策略方法被访问
 *
 * @author xiongsulong
 * @create 2020-10-29 20:05
 */
public class ConcreteStrategyB implements Strategy{

    @Override
    public void strategyMethod() {
        System.out.println("具体策略B的策略方法被访问!");
    }
}
