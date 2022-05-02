package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/21 16:07
 * Created by: wei.chen
 */
public class 斐波拉数列 {

    public Integer fib(int n) {
        if (n <= 1) return n;
        int first = 0;
        int second = 1;
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum = first + second;
            first = second;
            second = sum;
        }
        return sum;
    }
}
