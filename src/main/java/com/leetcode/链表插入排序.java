package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/21 17:27
 * Created by: wei.chen
 */
public class 链表插入排序 {

    public static Node insertSort(Node head) {
        if (head == null || head.next == null) return head;
        Node soldier = new Node(0, head), pre = soldier;
        while (head.next != null) {
            if (head.val < head.next.val) {
                head = head.next;
                continue;
            }
            pre = soldier;
            while (pre.next.val < head.next.val) {
                pre = pre.next;
            }

            Node current = head.next;
            head.next = current.next;

            current.next = pre.next;
            pre.next = current;
        }

        return soldier.next;
    }
}
