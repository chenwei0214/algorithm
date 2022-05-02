package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/21 16:56
 * Created by: wei.chen
 */
public class 链表中间节点 {
    public static Node findMiddle(Node head) {
        if (head == null || head.next == null) return head;
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
