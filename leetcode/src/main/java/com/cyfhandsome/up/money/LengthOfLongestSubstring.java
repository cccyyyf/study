package com.cyfhandsome.up.money;

import java.util.*;

/**
 * @author cyf
 * @date 2022/1/8 23:07
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 滑动窗口
 * 记录每个字符出现的索引信息
 * 记录左边界位置
 * 判断最大长度
 */
public class LengthOfLongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> charAndIndexMap = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if(charAndIndexMap.containsKey(s.charAt(i))){
                left = Math.max(left,charAndIndexMap.get(s.charAt(i))+1);
            }
            charAndIndexMap.put(s.charAt(i),i);
            maxLength = Math.max(maxLength,i-left+1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(lengthOfLongestSubstring(s));
    }
}
