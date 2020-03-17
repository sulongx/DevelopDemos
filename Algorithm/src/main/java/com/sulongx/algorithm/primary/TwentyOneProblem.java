package com.sulongx.algorithm.primary;

/**
 * 二十一点问题
 *赌场经典的二十一点游戏 4 中，每回合最少下注 1 枚硬币，赢了可以得到 2 枚硬币，输了硬币会被收走。
 *假设最开始只拥有 1 枚硬币，并且每回合下注 1 枚，那么 4 回合后还能剩余硬币的硬币枚数变化情况共有 6 种。
 *
 * @author Sulongx
 */
public class TwentyOneProblem {

    /**
     *
     * @param coins 硬币数
     * @param rounds 回合数
     * @return 符合参数输入的情况个数
     */
    public static int v1(int coins,int rounds){
        int coinCounts = coins * (1<<rounds);
        return coinCounts/2 + 1;
    }


    public static void main(String[] args) {
        System.out.println(1<<4);
        int i = v1(10, 24-1);
        System.out.println(i);
    }

}
