package com.sulongx.algorithm.letcode;

/**
 * @author sulongx
 * @version 1.0
 * @description 多数元素
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * @date 2024/3/7 11:28
 **/
public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2, 2, 2};

        MajorityElement majorityElement = new MajorityElement();
        int element = majorityElement.majorityElement(nums);
        System.out.println("多数元素是: " + element);
    }

    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;
        for (int num : nums) {
            if(count == 0){
                candidate = num;
            }
            count += (candidate == num) ? 1 : -1;
        }
        return candidate;
    }

}
