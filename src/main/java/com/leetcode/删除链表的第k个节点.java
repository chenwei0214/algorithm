package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/21 17:06
 * Created by: wei.chen
 */
public class 删除链表的第k个节点 {

    public static Node deleteKth(Node head, int k) {
        if (head == null) return null;
        if (k == 1) return head.next;
        Node current = head, pre = head;
        int i = 1;
        while (current != null && i < k) {
            i++;
            pre = current;
            current = current.next;
        }
        pre.next = pre.next.next;
        return head;

    }
}
