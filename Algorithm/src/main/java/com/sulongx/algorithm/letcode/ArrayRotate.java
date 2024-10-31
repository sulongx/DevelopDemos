package com.sulongx.algorithm.letcode;

import java.util.Arrays;

/**
 * @author sulongx
 * @version 1.0
 * @description 轮转数组
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * @date 2024/3/7 15:42
 **/
public class ArrayRotate {

    public static void main(String[] args) {
        int[] nums = {-1, -100, 3, 99, 3, 4, 2, 1, 9, 10, 11, 12};
        int k = 8;


        System.out.println("轮转前的数组: nums=\n" + Arrays.toString(nums));
        ArrayRotate arrayRotate = new ArrayRotate();
        int gcd = arrayRotate.gcd(nums.length, k);
        System.out.println("当前数组需要遍历的次数count=" + gcd);
        arrayRotate.rotate2(nums, k);
        System.out.println("轮转后的数组: nums=\n" + Arrays.toString(nums));
    }

    //环状替换
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = gcd(n, k);
        for (int start = 0; start < count; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
            } while (start != current);
        }
    }

    private int gcd(int x, int y) {
        while (y != 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }


    public void rotate3(int[] nums, int k) {

    }

}
