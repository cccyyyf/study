package com.cyfhandsome.up.money;

/**
 * @author cyf
 * @date 2022/3/20 17:41
 * 53. 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组 是数组中的一个连续部分。
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 *
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        //使用贪心,只有是正数的时候才相加
        int result = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            result = Math.max(count,result);
            //如果和小于0重新开始结算
            if(count < 0){
                count = 0;
            }
        }
       return result;
    }

    public int maxSubArray2(int[] nums) {
        //动态规划
        //1.确定dp数组和下标的含义 dp[i]包括下标i之前的最大连续子序列和为dp[i]
        //2.确定递推公式dp[i] = Math.max(nums[i]+dp[i-1],nums[i])
        //3.dp数组如何初始化
        //4.确认遍历数据
        //5.举例推导dp数组
        if(nums.length ==0){
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int result = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);
            result = Math.max(dp[i],result);
        }
        return result;
    }

    public static void main(String[] args) {
        MaxSubArray maxSubArray = new MaxSubArray();
        maxSubArray.maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4});
    }
}
