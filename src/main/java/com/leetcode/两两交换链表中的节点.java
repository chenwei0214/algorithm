package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/22 10:02
 * Created by: wei.chen
 */
public class 两两交换链表中的节点 {
    public Node swapPairs(Node head) {
        if (head == null || head.next == null) return head;
        Node soldier = new Node(), pre = soldier;
        soldier.next = head;
        while (pre.next != null && pre.next.next != null) {
            Node left = pre.next;
            Node right = pre.next.next;

            pre.next = right;
            left.next = right.next;
            right.next = left;

            //每次跳过两个节点
            pre = pre.next.next;
        }
        return soldier.next;
    }
}
