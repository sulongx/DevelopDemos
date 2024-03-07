package com.sulongx.algorithm.letcode;

import java.util.Arrays;

/**
 * @author sulongx
 * @version 1.0
 * @description 删除有序数组中的重复元素
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * @date 2024/3/6 18:08
 **/
public class ArraysRemoveRepetitionV2 {

    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 7, 8, 8, 9};

        ArraysRemoveRepetitionV2 arraysRemoveRepetitionV2 = new ArraysRemoveRepetitionV2();
        System.out.println("原数组nums = " + Arrays.toString(nums));
        int size = arraysRemoveRepetitionV2.removeDuplications(nums);
        System.out.println("新数组长度=" + size);
        System.out.println("新数组nums = " + Arrays.toString(nums));
    }

    public int removeDuplications(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }


}
