package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/23 10:06
 * Created by: wei.chen
 */
public class 排序链表_归并排序 {

    public Node sortList(Node head) {
        if (head == null || head.next == null) return head;
        Node middle = findMiddle(head);
        Node right = sortList(middle.next);
        middle.next = null;
        Node left = sortList(head);
        return mergeList(left, right);
    }

    public Node mergeList(Node left, Node right) {
        Node soldier = new Node(), p1 = left, p2 = right, p3 = soldier;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p3.next = p1;
                p1 = p1.next;
            } else {
                p3.next = p2;
                p2 = p2.next;
            }
            p3 = p3.next;
        }

        if (p1 != null) p3.next = p1;
        if (p2 != null) p3.next = p2;
        return soldier.next;
    }

    public Node findMiddle(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
