package com.sulongx.jvm.jit;

/**
 * @author sulongx
 * @version 1.0
 * @description 锁合并
 * @date 2022/4/24 17:55
 **/
public class NestedLocks {
    public static void main(String[] args) {
        new NestedLocks();
    }

    private java.util.Random random = new java.util.Random();

    private static final Object lock = new Object();

    public NestedLocks(){
        long sum = 0;
        for(int i = 0; i < 1_000_000; i ++){
            synchronized(lock){
                sum += random.nextInt();
            }

            synchronized(lock){
                sum -= random.nextInt();
            }
        }

        System.out.println(sum);
    }
}
