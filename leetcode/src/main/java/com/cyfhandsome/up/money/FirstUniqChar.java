package com.cyfhandsome.up.money;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cyf
 * @date 2022/2/22 9:50
 * 剑指 Offer 50. 第一个只出现一次的字符
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 *
 * 示例 1:
 *
 * 输入：s = "abaccdeff"
 * 输出：'b'
 * 示例 2:
 *
 * 输入：s = ""
 * 输出：' '
 * https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 */
public class FirstUniqChar {
    public char firstUniqChar(String s) {
        char[] charArr = s.toCharArray();
        Map<Character,Integer> charAppearNumMap = new HashMap<>();
        for (char c : charArr) {
            if(!charAppearNumMap.containsKey(c)){
                charAppearNumMap.put(c,1);
            }else {
                charAppearNumMap.put(c,charAppearNumMap.get(c)+1);
            }
        }
        for (char c : charArr) {
            if(charAppearNumMap.get(c) == 1){
                return c;
            }
        }
        return ' ';
    }
}
