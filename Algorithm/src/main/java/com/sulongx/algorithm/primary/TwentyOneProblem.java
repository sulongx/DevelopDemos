package com.sulongx.algorithm.primary;

/**
 * 二十一点问题
 *赌场经典的二十一点游戏 4 中，每回合最少下注 1 枚硬币，赢了可以得到 2 枚硬币，输了硬币会被收走。
 *假设最开始只拥有 1 枚硬币，并且每回合下注 1 枚，那么 4 回合后还能剩余硬币的硬币枚数变化情况共有 6 种。
 *
 * @author Sulongx
 */
public class TwentyOneProblem {

    private static int i = 24;

    private static long count = 0;


    public static void a(long n,int index){
        if(index == i){
            System.out.println("到达回合目标点：");
            count += (n * 2) -1;
            System.out.println(count);
            return;
        }
        for(long i = 1;i <= n*2;i ++){
            if(i != n){
                System.out.println("第"+index+"回合,上一回合的硬币结果:" + i);
                a(i,index + 1);
            }
        }
    }

    public static void main(String[] args) {
        a(10,1);
        System.out.println(count);
    }

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

}
