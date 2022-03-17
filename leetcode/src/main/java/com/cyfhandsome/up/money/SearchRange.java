package com.cyfhandsome.up.money;

/**
 * @author cyf
 * @date 2022/2/22 22:33
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 进阶：
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 * 示例 1：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 *
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 *
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 */
public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        /*int leftNum = getLeftNum(nums, target);
        int rightNum = getRightNum(nums, target);*/
        int leftNum = getNum(nums, target,true);
        int rightNum = getNum(nums, target,false);
        if(leftNum>rightNum){
            return new int[]{-1,-1};
        }
        return new int[]{leftNum,rightNum};
    }
    public int getNum(int[] nums,int target,boolean leftFlag){
        int left = 0,right = nums.length-1,res=-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            if(nums[mid] > target){
                right = mid-1;
            }else if(nums[mid] <target){
                left = mid+1;
            }else {
                res = mid;
                if(leftFlag){
                    right = mid-1;
                }else {
                    left = mid+1;
                }
            }
        }
        return res;
    }

    public int getLeftNum(int[] num,int target){
        int left = 0,right = num.length-1;
        while (left<=right){
            int mid = left+(right-left)/2;
            if(num[mid]>=target){
                right = mid-1;

            }else if(num[mid] < target){
                left = mid+1;
            }
        }
        return left;
    }

    public int getRightNum(int[] num,int target){
        int left = 0,right = num.length-1;
        while (left <=right){
            int mid = left+(right-left)/2;
            if(num[mid] > target){
                right = mid-1;
            }else {
                left = mid+1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        SearchRange searchRange = new SearchRange();
        searchRange.searchRange(new int[]{1,2,2,3,4,5},2);
    }
}
