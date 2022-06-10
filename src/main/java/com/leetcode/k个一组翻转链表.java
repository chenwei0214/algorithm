package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/5/11 19:33
 * Created by: wei.chen
 */
public class k个一组翻转链表 {

    public Node reverseKGroup(Node head, int k) {
        Node soldier = new Node(), pre = soldier;
        soldier.next = head;
        while (pre != null) {
            Node tail = pre;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return soldier.next;
                }
            }
            Node next = tail.next;
            Node[] reverse = reverse(head, tail);
            head = reverse[0];
            tail = reverse[1];

            pre.next = head;
            tail.next = next;
            pre = tail;
            head = tail.next;
        }
        return soldier.next;
    }

    public Node[] reverse(Node head, Node tail) {
        Node pre = tail.next, cur = head;
        while (pre != tail) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return new Node[]{tail, head};
    }

}
