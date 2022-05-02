package com.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/2/20 13:22
 * Created by: wei.chen
 */
public class Main {

    public static void main(String[] args) {
//        System.out.println(multiply("2", "3"));

        int arr[] = {1, 3, 2, 7, 4};
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static int test(String s) {
        int start = 0, end = 0, maxlen = 0;
        Set<Character> set = new HashSet<>();
        for (; end < s.length(); end++) {
            char element = s.charAt(end);
            if (element >= 'A') {
                while (set.size() == 1) {
                    set.remove(s.charAt(start++));
                }
                set.add(element);
            }
            if (end != start) {
                maxlen = Math.max(maxlen, end - start + 1);
            }
        }
        return maxlen;
    }

    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) return "0";
        String result = "0";
        for (int i = num1.length() - 1; i >= 0; i--) {
            StringBuilder res = new StringBuilder();
            for (int k = num1.length() - 1; k > i; k--) {
                res.append("0");
            }
            int n = 0;
            int x = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                int sum = x * y + n;
                res.append(sum % 10);
                n = sum / 10;
            }
            if (n != 0) {
                res.append(n % 10);
            }
            result = addStrings(result, res.reverse().toString());
        }
        return result;
    }

    public static String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sum = x + y + add;
            builder.append(sum % 10);
            add = sum / 10;
            i--;
            j--;
        }
        builder.reverse();
        return builder.toString();
    }

}
