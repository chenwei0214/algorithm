package com.linkedlist;

/**
 * Project Name: algorithm
 * Package Name: com.linkedlist
 * Date: 2021/12/7 10:28
 * Created by: wei.chen
 */
public class LinkedListQuickSort {

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

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static Node sort(Node head) {
        if (head == null || head.next == null) return head;

        //获取比较值
        Node middle = findMiddle(head);

        //比middle小的节点列表
        Node soldier1 = new Node();
        //比middle大的节点列表
        Node soldier2 = new Node();
        //与比较值相同的节点
        Node soldier3 = new Node();

        compare(middle, head, soldier1, soldier2, soldier3);

        Node left = sort(soldier1.next);
        Node right = sort(soldier2.next);

        return link(left, soldier3.next, right);
    }

    public static Node link(Node left, Node middle, Node right) {
        Node soldier = new Node(), head = soldier;
        head.next = left;
        head = findTail(soldier);
        head.next = middle;
        head = findTail(soldier);
        head.next = right;
        return soldier.next;
    }

    public static Node findTail(Node head) {
        if (head == null || head.next == null) return head;
        Node p = head;
        while (p.next != null) {
            p = p.next;
        }
        return p;
    }

    public static void compare(Node middle, Node head, Node soldier1, Node soldier2, Node soldier3) {
        Node p = head, q1 = soldier1, q2 = soldier2, q3 = soldier3;
        while (p != null) {
            Node node = new Node(p.val);
            if (p.val < middle.val) {
                q1.next = node;
                q1 = q1.next;
            } else if (p.val > middle.val) {
                q2.next = node;
                q2 = q2.next;
            } else {
                q3.next = node;
                q3 = q3.next;
            }
            p = p.next;
        }

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
