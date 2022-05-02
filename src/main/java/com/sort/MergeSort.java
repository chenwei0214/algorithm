package com.sort;

/**
 * Project Name: algorithm
 * Package Name: com.sort
 * Date: 2021/11/29 11:34
 * Created by: wei.chen
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {1, 7, 2, 4, 3, 4, 5};
        sort(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void sort(int[] arr, int start, int end) {
        if (start >= end) return;

        int middle = (start + end) / 2;
        sort(arr, start, middle);
        sort(arr, middle + 1, end);
        merge(arr, start, middle, end);
    }

    public static void merge(int[] arr, int start, int middle, int end) {
        int[] temp = new int[end - start + 1];

        int k = 0;
        int i = start;
        int j = middle + 1;
        while (i <= middle && j <= end) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= middle) {
            temp[k++] = arr[i++];
        }

        while (j <= end) {
            temp[k++] = arr[j++];
        }

        k = 0;
        while (start <= end) {
            arr[start++] = temp[k++];
        }
    }
}
