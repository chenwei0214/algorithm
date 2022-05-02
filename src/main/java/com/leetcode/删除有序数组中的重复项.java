package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/21 20:54
 * Created by: wei.chen
 */
public class 删除有序数组中的重复项 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 4};
        System.out.println(removeDuplicates(arr));
    }

    public static int removeDuplicates(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int slow = 1, fast = 1;
        while (fast < arr.length) {
            if (arr[fast] != arr[fast - 1]) {
                arr[slow++] = arr[fast];
            }
            fast++;
        }
        return slow;
    }

}
