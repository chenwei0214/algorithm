package com.linkedlist;

/**
 * Project Name: algorithm
 * Package Name: com.linkedlist
 * Date: 2021/11/25 14:08
 * Created by: wei.chen
 */
public class LinkedListAlgo {


    //单链表反转
    public Node reverse(Node head) {
        Node current = head, pre = null;

        while (current != null) {
            Node next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }

    public Node reverseWithRecursion(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node newHead = reverseWithRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    //检查环形链表
    public boolean checkCircle(Node head) {
        if (head == null) return false;
        Node slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    //合并有序链表
    public Node mergeTwoLists(Node head1, Node head2) {
        Node current = new Node(), p = current;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                p.next = head1;
                head1 = head1.next;
            } else {
                p.next = head2;
                head2 = head2.next;
            }
            p = p.next;
        }
        if (head1 != null) p.next = head1;
        if (head2 != null) p.next = head2;
        return current.next;
    }


    public static Node deleteKth(Node head, int k) {
        if (head == null) {
            return null;
        }
        if (k == 1) {
            return head.next;
        }

        Node current = head, pre = head;
        int i = 1;
        while (current != null && i < k) {
            i++;
            pre = current;
            current = current.next;
        }

        pre.next = pre.next.next;
        return head;
    }

    //删除倒数第k个节点
    public static Node deleteLastKth(Node head, int k) {
        if (head == null) return null;
        int i = 1;
        Node fast = head;
        while (fast != null && i < k) {
            fast = fast.next;
            i++;
        }

        if (fast == null) return head;

        Node slow = head;
        Node pre = null;
        while (fast.next != null) {
            fast = fast.next;
            pre = slow;
            slow = slow.next;
        }

        if (pre != null) {
            pre.next = pre.next.next;
        } else {
            head = head.next;
        }

        return head;
    }

    public static Node findMiddleNode(Node head) {
        if (head == null) return null;
        Node fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static boolean test(Node head) {
        if (head == null) return false;
        if (head.next == null) return true;

        //找到尾节点
        Node tail = head;
        int n = 1;
        while (tail.next != null) {
            n++;
            tail = tail.next;
        }

        int k = n / 2;

        //翻转了前半部分
        Node pre = null, current = head;
        while (k != 0) {
            Node next = current.next;
            current.next = pre;
            pre = current;
            current = next;
            k--;
        }

        Node first1 = pre, first2 = current;
        if (n % 2 > 0) first2 = current.next;
        while (first2 != null) {
            if (first1.val != first2.val) {
                return false;
            }
            first1 = first2.next;
            first2 = first2.next;
        }
        return true;
    }

    //插入排序
    public static Node insertionSort(Node head) {
        Node soldier = new Node(), pre = null;
        soldier.next = head;
        while (head != null && head.next != null) {
            if (head.val <= head.next.val) {
                continue;
            }
            pre = soldier;

            while (pre.next.val < head.next.val) {
                pre = pre.next;
            }
            //删除head.next节点
            Node curr = head.next;
            head.next = curr.next;
            //将head.next查到pre后面
            curr.next = pre.next;
            pre.next = curr;
        }
        return soldier.next;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        Node node5 = new Node(2);
        Node node1 = new Node(1);
        Node node2 = new Node(3);

        head.next = node5;
        node5.next = node1;
        node1.next = node2;

        head = insertionSort(head);

//        test(head);
//        System.out.println();
//        head = findMiddleNode(head);
        printList(head);
    }


    public static class Node {
        public int val;
        public Node next;

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
