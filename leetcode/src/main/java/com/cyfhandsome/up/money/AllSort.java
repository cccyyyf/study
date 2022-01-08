package com.cyfhandsome.up.money;

import java.sql.Array;

/**
 * @author cyf
 * @date 2021/11/20 21:59
 */
public class AllSort {

    /**
     * 冒泡排序
     * 重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。走访数列的工作是重复地进行直到没有再需要交换
     * 也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
     * 1、比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     * 2、对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     * 3、针对所有的元素重复以上的步骤，除了最后一个；
     * 4、重复步骤1~3，直到排序完成。
     * 时间复杂度O(n*n) 空间复杂度 O(1)
     */
    static class BubbleSort {
        static int[] bobbleSort(int[] arr) {
            if (arr.length <= 1) {
                return arr;
            }

            for (int i = 0; i < arr.length - 1 ; i++) {
                boolean flag = true;
                for (int j = 0; j < arr.length - 1 - i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        swap(arr, j, j + 1);
                        flag = false;
                    }
                }
                //如果没有发生交换说明已经排好序了
                if (flag) {
                    break;
                }
            }
            return arr;
        }

        public static void main(String[] args) {
            int[] arr = {3, 1, 5, 2, 8, 20, 6, 3, 22, 9};
            int[] ints = BubbleSort.bobbleSort(arr);
            for (int i : ints) {
                System.out.println(i);
            }
        }
    }

    /**
     * 选择排序
     * 在无序的序列中找到最小/最大元素，放到有序序列中的第一个，然后再从无序序列找到最小/最大元素，放到末尾，依次类推
     * 时间复杂度 O(n*n) 空间复杂度O(1)
     */
    static class SelectionSort {
        static int[] selectionSort(int[] arr) {
            if (arr.length <= 1) {
                return arr;
            }

            for (int i = 0; i < arr.length; i++) {
                int minNum = i;
                for (int j = i; j < arr.length; j++) {
                    if (arr[minNum] > arr[j]) {
                        minNum = j;
                    }
                }
                swap(arr, i, minNum);
            }
            return arr;
        }

        public static void main(String[] args) {
            int[] arr = {3, 1, 5, 2, 8, 20, 6, 3, 22, 9};
            int[] ints = SelectionSort.selectionSort(arr);
            for (int i : ints) {
                System.out.println(i);
            }
        }
    }

    /**
     * 插入排序
     * 通过构建有序序列，对于没有被排序的元素，从后像前遍历有序序列，找到相应位置并插入
     */
    static class InsertSort {
        static int[] insertSort(int[] arr) {
            if (arr.length <= 1) {
                return arr;
            }
            //默认第一个元素是有序序列
            for (int i = 0; i < arr.length - 1; i++) {
                int currentNum = arr[i + 1];
                int perIndex = i;
                //拿当前元素在有序序列中向前推进
                while (perIndex >= 0 && arr[perIndex] > currentNum) {
                    //将遍历到的元素向后移位
                    arr[perIndex + 1] = arr[perIndex];
                    perIndex--;
                }
                //将当前数据放到当前指针的后一个位置
                arr[perIndex + 1] = currentNum;
            }
            return arr;
        }

        public static void main(String[] args) {
            int[] arr = {3, 1, 5, 2, 8, 20, 6, 3, 22, 9};
            int[] ints = InsertSort.insertSort(arr);
            for (int i : ints) {
                System.out.println(i);
            }
        }
    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}