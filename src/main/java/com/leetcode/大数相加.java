package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/21 15:32
 * Created by: wei.chen
 */
public class 大数相加 {
    public static String addString(String s1, String s2) {
        int i = s1.length() - 1, j = s2.length() - 1, add = 0;
        StringBuilder ans = new StringBuilder();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? s1.charAt(i) - '0' : 0;
            int y = j >= 0 ? s2.charAt(j) - '0' : 0;
            int sum = x + y + add;
            ans.append(sum % 10);
            add = sum / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }

    public static void main(String[] args) {
        String s1 = "123";
        String s2 = "567";
        String ans = addString(s1, s2);
        System.out.println(ans);
    }
}
