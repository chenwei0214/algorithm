package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/21 16:51
 * Created by: wei.chen
 */
public class 判断环形链表 {

    public static boolean checkCirCle(Node head) {
        if (head == null || head.next == null) return false;
        Node slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) return true;
        }
        return false;
    }

}
