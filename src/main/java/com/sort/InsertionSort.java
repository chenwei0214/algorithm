package com.sort;

/**
 * Project Name: algorithm
 * Package Name: com.sort
 * Date: 2021/11/29 10:08
 * Created by: wei.chen
 */
public class InsertionSort {

    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (value < arr[j]) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = value;
        }
    }


}
