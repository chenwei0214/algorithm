package com.stack;

/**
 * Project Name: algorithm
 * Package Name: com.stack
 * Date: 2021/11/26 9:54
 * Created by: wei.chen
 */
public class LinkedListStack {

    public Node top;
    public int size = 0;

    private Node createNode(String val, Node next) {
        return new Node(val, next);
    }

    public String pop() {
        Node popNode = this.top;
        if (this.top == null) {
            System.out.println("stack is empty");
            return null;
        }
        this.top = popNode.next;
        if (this.size > 0) {
            this.size--;
        }
        return popNode.val;
    }

    public void push(String val) {
        this.top = this.createNode(val, this.top);
        this.size++;
    }

    public void clear() {
        this.top = null;
        this.size = 0;
    }

    public void print() {
        Node current = this.top;
        System.out.println("Print Stack");
        while (current != null) {
            System.out.print(current.val + "\t");
            current = current.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack();
        stack.push("www.baidu.com");
        stack.push("www.bibibi.com");
        stack.push("www.google.com");
        stack.print();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    public static class Node {
        public String val;
        public Node next;

        public Node(String val) {
            this(val, null);
        }

        public Node(String val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}
