package com.sulongx.algorithm.letcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sulongx
 * @version 1.0
 * @description 不重复最长字符串
 * 字符串组成:
 *       0 <= s.length <= 5 * 104
 *       s 由英文字母、数字、符号和空格组成
 * @date 2022/6/15 10:49
 **/
public class LongestSubstring {

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println("测试字符: " + s);
        LongestSubstring longestSubstring = new LongestSubstring();
        long time = System.nanoTime();
        System.out.println(longestSubstring.lengthOfLongestSubstring(s));
        System.out.println("用时: " + (System.nanoTime() - time) + " ns");
        System.out.println(longestSubstring.lengthOfLongestSubstringFast(s));
        time = System.nanoTime();
        System.out.println("用时: " + (System.nanoTime() - time) + " ns");
    }

    /**
     * 最长不重复字符串的长度
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s){
        if(s == null || s.length() == 0){
            return 0;
        }
        Set<Character> data = new HashSet<>();
        int maxLength = 0;
        for (int i = 0, posIndex = 0; i < s.length(); i++) {
            if(data.contains(s.charAt(i))){
                maxLength = Math.max(maxLength, i - posIndex);
                i = ++ posIndex;
                data.clear();
            }
            data.add(s.charAt(i));
        }
        return Math.max(maxLength, data.size());
    }


    public int lengthOfLongestSubstringFast(String s){
        if(s == null || s.length() == 0){
            return 0;
        }
        if(s.length() == 1){
            return 1;
        }
        int n = s.length(), res = 0;
        //ASCII 字符集缓存
        int[] index = new int[128];
        for (int i = 0, start = 0; i < n; i++) {
            //滑动窗口起始位置
            start = Math.max(index[s.charAt(i)], start);
            //滑动窗口最大值
            res = Math.max(res, i - start + 1);
            //缓存记录该字符在字符串中最新的索引值
            index[s.charAt(i)] = i;
        }
        return res;
    }
}
