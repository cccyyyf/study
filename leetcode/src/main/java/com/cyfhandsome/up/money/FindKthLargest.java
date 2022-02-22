package com.cyfhandsome.up.money;

/**
 * @author cyf
 * @date 2022/1/15 20:46
 * 215. 数组中的第K个最大元素
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 */
public class FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        int[] ints = quickSort(nums, 0, nums.length - 1);
        return ints[nums.length - k];
    }

    public static int[] quickSort(int[] nums, int left, int right) {

        if (left < right) {
            int randomIndex = (int) (Math.random() * (right - left) + left);
            swap(nums, left, randomIndex);
            int[] partition = partition(nums, left, right);
            quickSort(nums, left, partition[0] - 1);
            quickSort(nums, partition[1] + 1, right);
        }
        return nums;
    }

    private static int[] partition(int nums[], int left, int right) {
        int less = left - 1;
        int more = right;
        while (left < more) {
            if (nums[left] < nums[right]) {
                swap(nums, ++less, left++);
            } else if (nums[left] > nums[right]) {
                swap(nums, --more, left);
            } else {
                left++;
            }
        }
        swap(nums, more, right);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
