package com.linkedlist;

/**
 * Project Name: algorithm
 * Package Name: com.linkedlist
 * Date: 2021/12/7 9:44
 * Created by: wei.chen
 */
public class LinkedListMergeSort {

    public static void main(String[] args) {
        Node head = new Node(5);
        Node node1 = new Node(7);
        Node node2 = new Node(6);
        Node node3 = new Node(1);
        Node node4 = new Node(3);
        Node node5 = new Node(4);
        Node node6 = new Node(2);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        Node head1 = sort(head);

        printList(head1);

    }

    public static Node sort(Node head) {
        if (head == null || head.next == null) return head;
        //找到中间节点
        Node middle = findMiddle(head);

        //对左右两边分别排序
        Node right = sort(middle.next);
        middle.next = null;
        Node left = sort(head);
        //合并两个排好的链表
        return merge(right, left);
    }

    public static Node findMiddle(Node head) {
        if (head == null || head.next == null) return head;

        Node fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node merge(Node head1, Node head2) {
        Node solier = new Node(), pre = solier;

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
        return solier.next;
    }

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
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
