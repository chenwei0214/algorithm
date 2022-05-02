package com.sort;

/**
 * Project Name: algorithm
 * Package Name: com.sort
 * Date: 2021/11/29 17:09
 * Created by: wei.chen
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {4, 7, 2, 1, 3, 4, 5};

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void sort1(int[] arr, int start, int end) {

        if (start >= end) return;
        int partition = partition1(arr, start, end);
        sort1(arr, start, partition - 1);
        sort1(arr, partition, end);
    }


    public static int partition1(int[] arr, int start, int end) {
        //选择第一个元素作为分界
        int partition = start;
        int index = partition + 1;
        for (int i = index; i <= end; i++) {
            if (arr[i] < arr[partition]) {
                swap(arr, i, index);
                index++;
            }
        }

        swap(arr, partition, index - 1);
        return index;
    }

    public static void swap(int[] arr, int index1, int index2) {
        if (index1 == index2) return;
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }


    public static int partition2(int[] arr, int start, int end) {
        int pivot = arr[start];
        while (start < end) {
            while (start < end && arr[end] >= pivot) {
                end--;
            }
            if (start < end) {
                arr[start] = arr[end];
                start++;
            }
            while (start < end && arr[start] < pivot) {
                start++;
            }
            if (start < end) {
                arr[end] = arr[start];
                end--;
            }
        }
        arr[start] = pivot;
        return start;
    }

}
