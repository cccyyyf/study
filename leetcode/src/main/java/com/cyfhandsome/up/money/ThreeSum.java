package com.cyfhandsome.up.money;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author cyf
 * @date 2022/2/22 20:31
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 */
public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        if(nums==null||nums.length <3){
            return new ArrayList<>();
        }
        List<List<Integer>> resultList = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            //如果第一个数不是负数，那么不会产生三数相加等于0的情况
            if(nums[i] >0){
                return resultList;
            }
            //跳过与上一个数相等的情况
            if(i>0&&nums[i] ==nums[i-1]){
                continue;
            }
            //左指针
            int left = i+1;
            //右指针
            int right = nums.length-1;
            while (left<right){
                //获取三数之和
                int result = nums[i]+nums[left]+nums[right];
                //当结果小于0时，证明前面有负数并负数太大，导致结果<0,左指针后移，并且过滤掉重复数字

                if(result<0){
                    while (left<right&& nums[left] ==nums[++left]) {

                    }
                }else if(result >0){
                    //当结果大于0时，证明后面面有正数数并正数太大，导致结果>0,左指针前移，并且过滤掉重复数字
                    while (left<right&& nums[right]==nums[--right]) {

                    }
                }else {
                    //当结果等于0时，左右指针前后移动，并去除重复数字
                    resultList.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    while (left<right&& nums[left] ==nums[++left]);
                    while (left<right&& nums[right]==nums[--right]);
                }
            }

        }
        return resultList;
    }

    public static void main(String[] args) {
        threeSum(new int[] {-1,0,1,2,-1,-4});
    }
}
