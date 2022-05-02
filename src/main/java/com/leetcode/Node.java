package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/21 16:42
 * Created by: wei.chen
 */
public class Node {
    int val;
    Node next;
    public Node(){}
    public Node(int val){
        this.val = val;
    }
    public Node(int val,Node next){
        this.val = val;
        this.next = next;
    }
}
