package com.cyfhandsome.up.money;

import java.util.Arrays;

/**
 * @author cyf
 * @date 2021/10/21 19:32
 */
public class StudyLeetCode {
    /**
     * 一个数组中 获取两个只出现奇数次的数字
     *
     * @param arr 数组
     */
    public static void printTwoOddTimeNum(int[] arr) {
        int eor = 0;
        for (int i : arr) {
            eor ^= i;
        }
        //获取eor最右边一位的数
        //eor -> 1010111100 ，~取反 0101000011 ,加1 -> 0101000100
        //& eor -> 1010111100 & 0101000100 -> 0000000100
        int rightOne = eor & (~eor + 1);
        int onlyOne = 0;
        for (int cur : arr) {
            if ((cur & rightOne) == 0) {
                onlyOne ^= cur;
            }
        }
        System.out.printf(onlyOne + " " + (onlyOne ^ eor));
    }


    static class Sort {
        /**
         * 插入排序，时间复杂度O(n^2) 空间负复杂度 O(1)
         *
         * @param arr
         */
        public static void insertionSort(int[] arr) {
            //一次让0-0，0-1，0-2...0-arr.length-1 位置上的数据有序
            if (arr == null || arr.length < 2) {
                return;
            }
            for (int i = 1; i < arr.length; i++) {
                for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                    swap(arr, j, j + 1);
                }
            }
            for (int i : arr) {
                System.out.printf(i + "");
            }
        }

        public static void main(String[] args) {
            int[] arr = {3, 2, 5, 4, 2, 3, 3};
            insertionSort(arr);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
