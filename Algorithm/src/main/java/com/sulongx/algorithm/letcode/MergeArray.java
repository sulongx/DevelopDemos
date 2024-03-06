package com.sulongx.algorithm.letcode;

import java.util.Arrays;

/**
 * @author sulongx
 * @version 1.0
 * @description 合并数组
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * <p>
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * <p>
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 * @date 2024/3/6 15:56
 **/
public class MergeArray {

    public static void main(String[] args) {
        int[] nums1 = {2, 0};
        int m = 1;
        int[] nums2 = {1};
        int n = 1;

        MergeArray mergeArray = new MergeArray();
        mergeArray.merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }


//    public void merge(int[] nums1, int m, int[] nums2, int n) {
//        int[] result = new int[m + n];
//        int p1 = 0, p2 = 0;
//        for (int i = 0; i < m + n; i++) {
//            if (p1 < m && p2 < n) {
//                if (nums1[p1] < nums2[p2]) {
//                    result[i] = nums1[p1];
//                    p1++;
//                } else if (nums1[p1] == nums2[p2]) {
//                    result[i] = nums1[p1];
//                    result[++i] = nums2[p2];
//                    p1++;
//                    p2++;
//                } else {
//                    result[i] = nums2[p2];
//                    p2++;
//                }
//            } else if (p1 < m && p2 == n) {
//                result[i] = nums1[p1];
//                p1++;
//            } else if (p2 < n && p1 == m) {
//                result[i] = nums2[p2];
//                p2++;
//            }
//        }
//        if (m + n >= 0) System.arraycopy(result, 0, nums1, 0, m + n);
//    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
    }

}
