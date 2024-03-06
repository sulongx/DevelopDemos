package com.sulongx.algorithm.letcode;

import java.util.Arrays;

/**
 * @author sulongx
 * @version 1.0
 * @description 删除有序数组中重复的元素
 * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
 * <p>
 * 考虑 nums 的唯一元素的数量为 k ，你需要做以下事情确保你的题解可以被通过：
 * <p>
 * 更改数组 nums ，使 nums 的前 k 个元素包含唯一元素，并按照它们最初在 nums 中出现的顺序排列。nums 的其余元素与 nums 的大小不重要。
 * 返回 k 。
 * @date 2024/3/6 17:44
 **/
public class ArraysRemoveRepetition {

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4,5,5,6,7,8,8,9};

        ArraysRemoveRepetition arraysRemoveRepetition = new ArraysRemoveRepetition();
        System.out.println("原数组nums = " + Arrays.toString(nums));
        int size = arraysRemoveRepetition.removeDuplicates(nums);
        System.out.println("新数组长度=" + size + ", nums = " + Arrays.toString(nums));
    }

    public int removeDuplicates(int[] nums) {
        int target = nums[0];
        int p = 1, cur = 1;
        while (p < nums.length) {
            if(nums[p] == target){
                p ++;
            }else {
                nums[cur] = nums[p];
                target = nums[p];
                cur ++;
            }
        }
        return cur;
    }
}
