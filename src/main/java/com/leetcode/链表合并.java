package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/21 16:59
 * Created by: wei.chen
 */
public class 链表合并 {

    public static Node mergeList(Node head1, Node head2) {
        Node soldier = new Node(), pre = soldier;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                pre.next = head1;
                head1 = head1.next;
            } else {
                pre.next = head2;
                head2 = head2.next;
            }
            pre = pre.next;
        }
        if (head1 != null) pre.next = head1;
        if (head2 != null) pre.next = head2;
        return soldier.next;
    }
}
