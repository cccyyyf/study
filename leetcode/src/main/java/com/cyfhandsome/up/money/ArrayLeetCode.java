package com.cyfhandsome.up.money;


import java.util.Arrays;
import java.util.logging.Logger;

/**
 * @author cyf
 * @date 2021/9/24 21:01
 * 数组
 */
public class ArrayLeetCode {

    private static final Logger log = Logger.getLogger("ArrayLeetCode");


    /**
     * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target ，写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
     * 示例 1:
     * <p>
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
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    static class BinarySearch {
        //二分查找
        public static int binarySearch(int[] array, int target) {
            int left = 0;
            int right = array.length - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (array[mid] < target) {
                    left = mid + 1;
                } else if (array[mid] > target) {
                    right = mid - 1;
                } else {
                    return mid;
                }
            }

            return -1;
        }

        public static void main(String[] args) {
            int[] array = {-1, 0, 3, 5, 9, 12};
            log.info("结果:" + binarySearch(array, 9));
        }
    }


    /**
     * 35.搜索插入位置
     * <p>
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * <p>
     * 请必须使用时间复杂度为 O(log n) 的算法。
     * <p>
     *  
     * <p>
     * 示例 1:
     * 输入: nums = [1,3,5,6], target = 5
     * 输出: 2
     * <p>
     * 示例2:
     * 输入: nums = [1,3,5,6], target = 2
     * 输出: 1
     * <p>
     * 示例 3:
     * 输入: nums = [1,3,5,6], target = 7
     * 输出: 4
     * <p>
     * 示例 4:
     * 输入: nums = [1,3,5,6], target = 0
     * 输出: 0
     * <p>
     * 示例 5:
     * 输入: nums = [1], target = 0
     * 输出: 0
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-insert-position
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    static class SearchInsert {
        public static int searchInsert(int[] array, int target) {
            int left = 0;
            int right = array.length - 1;

            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (array[mid] < target) {
                    left = mid + 1;
                } else if (array[mid] > target) {
                    right = mid - 1;
                } else {
                    return mid;
                }
            }

            return right + 1;
        }

        public static void main(String[] args) {
            int[] array = {-1, 0, 3, 5, 9, 12};
            log.info("结果:" + (searchInsert(array, -7)));
        }
    }

    /**
     * 34.在排序数组中查找元素的第一个和最后一个位置
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回[-1, -1]。
     * 进阶：
     * 你可以设计并实现时间复杂度为O(log n)的算法解决此问题吗？
     * <p>
     * 示例 1：
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * <p>
     * 示例2：
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     * 示例 3:
     * 输入：nums = [], target = 0
     * 输出：[-1,-1]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    static class SearchRange {
        public static int[] searchRange(int[] allDataArray, int target) {
            //先获取>=target的第一个
            int left = getData(allDataArray, target);
            //再获取 >target的第一个
            int right = getData(allDataArray,target+1);
            if(left == allDataArray.length || allDataArray[left] != target
                    ||( allDataArray.length >1 && allDataArray[right -1] != target )){
                return new int[]{-1,-1};
            }

            return new int[]{left,right-1};

        }

        public static int getData(int[] allDataArray, int target) {
            int left = 0;
            int right = allDataArray.length ;
            while (left < right){
                int mid = (left + right) >> 1;
                if(allDataArray[mid] >= target){
                    right = mid;
                }else {
                    left = mid + 1;
                }
            }
            return left;
        }

        public static void main(String[] args) {
            log.info(Arrays.toString(searchRange(new int[]{2,2},2)));
        }
    }

    /**
     * 给你一个非负整数 x ，计算并返回x的 算术平方根 。
     *
     * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
     *
     * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
     *
     * 示例 1：
     *
     * 输入：x = 4
     * 输出：2
     * 示例 2：
     *
     * 输入：x = 8
     * 输出：2
     * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sqrtx
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    static class MySqrt {
        public static int mySqrt(int x) {
            if(x <=1){
                return x;
            }
            int max = x;
            int min = 0;
            while (max - min >1){
                int mid = (min + max) >> 1;
                if(x/mid < mid){
                    max = mid;
                }else {
                    min = mid;
                }
            }
            return min;
        }

        public static void main(String[] args) {

            log.info(String.valueOf(mySqrt(16)));
        }
    }


    /**
     * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
     *
     * 进阶：不要 使用任何内置的库函数，如 sqrt 。
     *
     * 示例 1：
     *
     * 输入：num = 16
     * 输出：true
     * 示例 2：
     *
     * 输入：num = 14
     * 输出：false
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-perfect-square
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    static class IsPerfectSquare {
        public static boolean isPerfectSquare(int num) {
            //利用二分查找
            int min = 0;
            int max = num;
            while (min <= max){
                int mid = (min + max) >> 1;
                if(mid * mid == num){
                    return true;
                }else if((long)mid * mid > num){
                    max = mid - 1;
                }else {
                    min = mid + 1;
                }
            }
            return false;
        }

        public static void main(String[] args) {
            log.info(String.valueOf(isPerfectSquare(16)));
        }
    }


}
