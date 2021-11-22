package com.cyfhandsome.up.money;

import java.util.Arrays;

/**
 * @author cyf
 * @date 2021/11/17 16:13
 */
public class BinarySearchLeetCode {

    /**
     * 704、给定一个n个元素有序的（升序）整型数组nums 和一个目标值target
     * ，写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
     * <p>
     * 示例 1:
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     * 示例2:
     * <p>
     * 输入: nums = [-1,0,3,5,9,12], target = 2
     * 输出: -1
     * 解释: 2 不存在 nums 中因此返回 -1
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-search
     */
    static class BinarySearch {
        public static int search(Integer[] nums, int target) {
            int start = 0;
            int end = nums.length - 1;
            while (start <= end) {
                int mid = start + ((end - start) >> 1);
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] > target) {
                    end = mid - 1;
                } else if (nums[mid] < target) {
                    start = mid + 1;
                }
            }

            return -1;
        }

        public static void main(String[] args) {
            System.out.printf(BinarySearch.search((Integer[]) Arrays.asList(-1, 0, 3, 5, 9, 12).toArray(), 9) + "");
        }
    }

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 请必须使用时间复杂度为 O(log n) 的算法。
     * 示例 1:
     * <p>
     * 输入: nums = [1,3,5,6], target = 5
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: nums = [1,3,5,6], target = 2
     * 输出: 1
     * 示例 3:
     * <p>
     * 输入: nums = [1,3,5,6], target = 7
     * 输出: 4
     * 示例 4:
     * <p>
     * 输入: nums = [1,3,5,6], target = 0
     * 输出: 0
     * 示例 5:
     * <p>
     * 输入: nums = [1], target = 0
     * 输出: 0
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-insert-position
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    static class SearchInsert {
        public int searchInsert(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;

            while (left < right) {
                int mid = left + ((right - left) >> 1);
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid;
                }
            }
            return right;
        }
    }


}
