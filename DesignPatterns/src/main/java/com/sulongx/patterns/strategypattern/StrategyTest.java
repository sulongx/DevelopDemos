package com.sulongx.patterns.strategypattern;

/**
 * 描述:
 *
 * @author xiongsulong
 * @create 2020-10-29 20:08
 */
public class StrategyTest {
    public static void main(String[] args) {
        Context context = new Context();

        //具体策略A
        Strategy strategyA = new ConcreteStrategyA();
        context.setStrategy(strategyA);
        context.strategyMethod();

        //具体策略B
        Strategy strategyB = new ConcreteStrategyB();
        context.setStrategy(strategyB);
        context.strategyMethod();
    }
}
