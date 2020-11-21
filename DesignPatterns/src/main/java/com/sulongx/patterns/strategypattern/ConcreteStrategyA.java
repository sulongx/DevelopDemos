package com.sulongx.patterns.strategypattern;

/**
 * 描述:
 * 具体策略A
 *
 * @author xiongsulong
 * @create 2020-10-29 20:04
 */
public class ConcreteStrategyA implements Strategy{

    @Override
    public void strategyMethod() {
        System.out.println("具体策略A的策略方法被访问!");
    }
}
