package com.leetcode;

import java.util.Arrays;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/21 20:39
 * Created by: wei.chen
 */
public class 最长公共前缀 {

    public static void main(String[] args) {
        String[] strs = {"hellow","hello","hello3"};
        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String str0 = strs[0];
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str0.length(); i++) {
            char ch = str0.charAt(i);
            boolean flag = true;
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() < (i + 1) || strs[j].charAt(i) != ch) {
                    flag = false;
                    break;
                }
            }
            if (!flag) break;
            builder.append(ch);
        }
        return builder.toString();
    }
}
