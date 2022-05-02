package com.linkedlist;

/**
 * Project Name: algorithm
 * Package Name: com.linkedlist
 * Date: 2021/12/7 13:50
 * Created by: wei.chen
 */
public class LinkedListInsertionSort {

    public static Node sort(Node head) {
        if (head == null || head.next == null) return head;

        Node soldier = new Node(0, head), pre = null;
        while (head.next != null) {
            if (head.val <= head.next.val) {
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

    public static class Node {
        private int val;
        private Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}
