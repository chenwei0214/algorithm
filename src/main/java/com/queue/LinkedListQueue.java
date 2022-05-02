package com.queue;

/**
 * Project Name: algorithm
 * Package Name: com.queue
 * Date: 2021/11/26 11:24
 * Created by: wei.chen
 */
public class LinkedListQueue {
    private Node head;
    private Node tail;

    public boolean enqueue(String val) {
        if (tail == null) {
            Node newNode = new Node(val);
            head = newNode;
            tail = newNode;
        } else {
            tail.next = new Node(val);
            tail = tail.next;
        }
        return true;
    }

    public String dequeue() {
        if (head == null) return null;

        String val = head.val;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return val;
    }

    public void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.val+"\t");
            current = current.next;
        }
        System.out.println();
    }

    public static class Node {
        public String val;
        public Node next;

        public Node() {
        }

        public Node(String val) {
            this.val = val;
        }

        public Node(String val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}
