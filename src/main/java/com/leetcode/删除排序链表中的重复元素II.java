package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/22 10:33
 * Created by: wei.chen
 */
public class 删除排序链表中的重复元素II {

    public Node deleteDuplicates(Node head) {
        if (head == null || head.next == null) return head;
        Node soldier = new Node();
        soldier.next = head;
        Node p1 = soldier, p2 = soldier.next.next;

        while (p1.next != null) {
            boolean flag = false;
            while (p2 != null) {
                if (p1.next.val == p2.val) {
                    //删除p2
                    flag = true;
                    p1.next.next = p2.next;
                    p2 = p2.next;
                } else {
                    p2 = p2.next;
                    break;
                }
            }
            if (flag) {
                p1.next = p1.next.next;
            } else {
                p1 = p1.next;
            }
        }

        return soldier.next;
    }
}
