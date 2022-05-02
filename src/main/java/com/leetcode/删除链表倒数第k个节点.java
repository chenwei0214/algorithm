package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/21 17:15
 * Created by: wei.chen
 */
public class 删除链表倒数第k个节点 {

    public static Node deleteLastKth(Node head, int k) {
        if (head == null) return null;
        Node fast = head;
        int i = 1;
        while (fast != null && i < k) {
            fast = fast.next;
            i++;
        }
        //没有找到
        if (fast == null) return head;

        Node slow = head, pre = null;
        while (fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }

        if (pre != null) {
            pre.next = pre.next.next;
        } else {
            head = head.next;
        }
        return head;
    }

}
