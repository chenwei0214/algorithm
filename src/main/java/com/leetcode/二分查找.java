package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/4/7 9:57
 * Created by: wei.chen
 */
public class 二分查找 {

    public static int search(int[] nums, int val) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length - 1;

        while (start <= end) {
            int middle = (start + end) / 2;
            if (nums[middle] == val) {
                return middle;
            } else if (nums[middle] > val) {
                end = middle;
            } else if (nums[middle] < val) {
                start = middle;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int []  arr={1,2,3,4,5,6};

        System.out.println(search(arr,2));
    }
}
