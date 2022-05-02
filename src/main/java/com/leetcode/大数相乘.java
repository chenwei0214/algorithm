package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/21 15:44
 * Created by: wei.chen
 */
public class 大数相乘 {

    public static void main(String[] args) {
        String s1 = "12", s2 = "12";
        String multiply = multiply(s1, s2);
        System.out.println(multiply);
    }

    public static String multiply(String s1, String s2) {
        String result = "0";
        for (int i = s1.length() - 1; i >= 0; i--) {
            StringBuilder num = new StringBuilder();
            for (int k = s1.length() - 1; k > i; k--) {
                num.append("0");
            }
            int n = 0;
            int x = s1.charAt(i) - '0';
            for (int j = s2.length() - 1; j >= 0; j--) {
                int y = s2.charAt(j) - '0';
                int z = x * y + n;
                num.append(z % 10);
                n = z / 10;
            }
            if (n != 0) {
                num.append(n % 10);
            }
            result = addString(result, num.reverse().toString());
        }
        return result;
    }

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
}
