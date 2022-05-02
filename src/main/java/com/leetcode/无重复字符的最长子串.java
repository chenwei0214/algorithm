package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/21 15:08
 * Created by: wei.chen
 */
public class 无重复字符的最长子串 {
    public static void main(String[] args) {

    }

    public static int lengthOfLongestSubstring01(String str) {
        int maxlen = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int start = 0, end = 0; end < str.length(); end++) {
            char ch = str.charAt(end);
            if (map.containsKey(ch)) {
                start = Math.max(start, map.get(ch) + 1);
            }
            map.put(ch, end);
            maxlen = Math.max(maxlen, end - start + 1);
        }
        return maxlen;
    }

    public static int lengthOfLongestSubstring02(String str) {
        int maxlen = 0, start = 0, end = 0;
        Set<Character> set = new HashSet<>();
        while (end < str.length()) {
            char ch = str.charAt(end);
            while(set.contains(ch)){
                set.remove(str.charAt(start++));
            }
            set.add(ch);
            maxlen = Math.max(maxlen,set.size());
        }
        return maxlen;
    }
}
