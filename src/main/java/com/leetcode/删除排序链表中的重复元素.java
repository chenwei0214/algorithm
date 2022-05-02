package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/22 10:26
 * Created by: wei.chen
 */
public class 删除排序链表中的重复元素 {

    public Node deleteDuplicates(Node head) {
        if (head == null || head.next == null) return head;
        Node pre = head;
        while (pre.next != null) {
            if (pre.val == pre.next.val) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }
        return head;
    }
}
