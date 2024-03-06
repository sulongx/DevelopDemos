package com.sulongx.algorithm.letcode;

import java.util.Arrays;

/**
 * @author sulongx
 * @version 1.0
 * @description 移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * @date 2024/3/6 16:57
 **/
public class ArraysRemoveEle {


    public static void main(String[] args) {
        int[] nums = {1};
        int val = 1;

        ArraysRemoveEle arraysRemoveEle = new ArraysRemoveEle();
        System.out.println("原函数nums = " + Arrays.toString(nums) + ", 需要去除的元素: " + val);
        int size = arraysRemoveEle.removeElement(nums, val);
        System.out.println("新函数的长度=" + size + ", nums = " + Arrays.toString(nums));
    }

//    public int removeElement(int[] nums, int val) {
//        int removeSize = 0;
//        int curIndex = 0, tailIndex = nums.length - 1;
//        while (curIndex <= tailIndex) {
//            if (nums[tailIndex] == val) {
//                nums[tailIndex] = 0;
//                tailIndex--;
//                removeSize++;
//                continue;
//            }
//            if (nums[curIndex] == val) {
//                nums[curIndex] = nums[tailIndex];
//                nums[tailIndex] = 0;
//                tailIndex--;
//                removeSize++;
//            }
//            curIndex++;
//        }
//        return nums.length - removeSize;
//    }

    public int removeElement(int[] nums, int val) {
        int left = 0, right = nums.length;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right - 1];
                nums[right - 1] = 0;
                right--;
            } else {
                left++;
            }
        }
        return left;
    }
}
