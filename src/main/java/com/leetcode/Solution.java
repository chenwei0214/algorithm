package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2021/12/1 12:10
 * Created by: wei.chen
 */
public class Solution {

    /**
     * 不连续字符串最长的长度
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxlen = 0;
        for (int start = 0, end = 0; end < s.length(); end++) {
            Character element = s.charAt(end);
            if (map.containsKey(element)) {
                start = Math.max(map.get(element), start);
            }
            maxlen = Math.max(maxlen, end - start + 1);
            map.put(element, end + 1);
        }
        return maxlen;
    }


    public static boolean maxPower(int x) {
        if (x < 0) {
            return false;
        }
        String str = "" + x;
        for (int start = 0, end = str.length() - 1; start <= end; start++, end--) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
        }
        return true;
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 1) return s;

        int n = s.length();
        int maxlen = 0;
        StringBuilder builder = null;
        Map<Integer, String> map = new HashMap<>();
        for (int start = 0; start < n - 1; start++) {

            builder = new StringBuilder();
            char startChar = s.charAt(start);

            for (int end = start; end < n; end++) {
                char endChar = s.charAt(end);
                builder.append(endChar);
                int len = end - start + 1;
                if (startChar == endChar && len > maxlen) {
                    boolean flag = true;
                    //判断builder是不是回文字符串
                    for (int i = 0, j = builder.length() - 1; i <= j; i++, j--) {
                        if (builder.charAt(i) != builder.charAt(j)) {
                            flag = false;
                            break;
                        }
                    }

                    if (flag) {
                        maxlen = len;
                        map.put(maxlen, builder.toString());
                    }
                }
            }
        }
        return map.get(maxlen);
    }

    public static int removeElement(int[] nums, int val) {
        int fast = 0, slow = 0;
        while (fast != nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    public static int removeElement(int[] nums) {
        int fast = 1, slow = 1;
        while (fast != nums.length) {
            if (nums[fast-1] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }


    public static void main(String[] args) {
        int[] nums = {1};
        int len = removeElement(nums,1);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + "\n");
        }
    }
}
