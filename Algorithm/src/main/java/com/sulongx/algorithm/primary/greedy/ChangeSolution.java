package com.sulongx.algorithm.primary.greedy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sulongx
 * @title 找零问题
 * @details
 * @date 2023/1/2
 */
public class ChangeSolution {

    private BigDecimal threshold = BigDecimal.valueOf(0.8f);

    private List<List<Integer>> result = new ArrayList<>();

    public void greedyMoney(int[] coins, int money){
        int tmp = money;
        for (int i = 0; i < coins.length; i++) {
            List<Integer> item = new ArrayList<>();
            for (int j = i; j < coins.length; j++) {
                while (money >= coins[j] && money > 0){
                    money = money - coins[j];
                    item.add(coins[j]);
                }
            }
            result.add(item);
            money = tmp;
        }
        //精准度排除
        result = result.stream().filter(item -> {
            Integer result = item.stream().reduce(0, Integer::sum);
            return BigDecimal.valueOf(result).divide(BigDecimal.valueOf(tmp), 2, BigDecimal.ROUND_DOWN).compareTo(threshold) > 0;
        }).collect(Collectors.toList());;
    }


    public static void main(String[] args) {
        ChangeSolution changeSolution = new ChangeSolution();
        changeSolution.greedyMoney(new int[]{100, 50, 20, 10, 5, 2, 1}, 1000);
        System.out.println("组合个数: " + changeSolution.result.size());
        System.out.println(changeSolution.result);
    }
}
