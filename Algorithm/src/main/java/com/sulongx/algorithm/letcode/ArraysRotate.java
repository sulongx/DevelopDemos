package com.sulongx.algorithm.letcode;

import java.util.Arrays;

/**
 * @author sulongx
 * @title 轮转数组
 * @details 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * @date 2024/3/10
 */
public class ArraysRotate {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;

        ArraysRotate arraysRotate = new ArraysRotate();
        System.out.println("原数组：\n" + Arrays.toString(nums));
        System.out.println("轮转k=" + k + "次数后的数组:");
        arraysRotate.rotate2(nums, k);
        System.out.println(Arrays.toString(nums));
    }


    //使用额外的数组,原数组元素下标新的位置为 (i + k) % n
    public void rotate1(int[] nums, int k) {
        int n = nums.length;
        int[] newArray = new int[n];
        for (int i = 0; i < n; i++) {
            newArray[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArray, 0, nums, 0, n);
    }

    //环状替换
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = gcd(k, n);
        for (int start = 0; start < count; ++start) {
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
        return y > 0 ? gcd(y, x % y) : x;
    }

}
