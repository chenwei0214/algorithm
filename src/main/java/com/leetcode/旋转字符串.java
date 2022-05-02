package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/4/7 20:28
 * Created by: wei.chen
 */
public class 旋转字符串 {

    public static boolean rotateString(String s, String goal) {
        if (s == null || goal == null) return false;
        for (int i = 1; i < s.length(); i++) {
            String subStr1 = s.substring(i);
            String subStr2 = s.substring(0, i);
            String rotateStr = subStr1 + subStr2;
            if(rotateStr.equals(goal)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s1="abcde";
        String s2="cdeab";

        System.out.println(rotateString(s1,s2));
    }
}
