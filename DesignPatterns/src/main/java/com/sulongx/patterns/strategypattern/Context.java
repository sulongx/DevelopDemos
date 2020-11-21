package com.sulongx.patterns.strategypattern;

/**
 * 描述:
 * 环境类
 *
 * @author xiongsulong
 * @create 2020-10-29 20:06
 */
public class Context {
    private Strategy strategy;

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void strategyMethod(){
        this.strategy.strategyMethod();
    }
}
