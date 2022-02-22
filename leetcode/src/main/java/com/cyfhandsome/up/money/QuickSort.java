package com.cyfhandsome.up.money;

import java.util.Arrays;

/**
 * @author cyf
 * @date 2022/1/10 23:35
 */
public class QuickSort {



    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int randomIndex = (int) Math.random()*(right-left) + left;
            swap(arr,left,randomIndex);

            int[] ints = partition1(arr, left, right);
            quickSort(arr,left,ints[0]-1);
            quickSort(arr,ints[1]+1,right);
        }

    }

    private static int[] partition1(int[] arr,int left,int right){
        int less = left-1;
        int more  = right;
        while (left<more){
            if(arr[left] < arr[right]){
                swap(arr,++less,left++);
            }else if(arr[left] > arr[right]){
                swap(arr,--more,left);
            }else {
                left++;
            }
        }
        swap(arr,more,right);
        return new int[]{less+1,more};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) throws Exception {
        int[] arr = {3, 2, 1, 5, 6, 4};
        quickSort(arr, 0, arr.length - 1);
        for (int anInt : arr) {
            System.out.println(anInt);
        }
    }
}
