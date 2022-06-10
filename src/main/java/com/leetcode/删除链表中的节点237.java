package com.leetcode;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/5/7 10:45
 * Created by: wei.chen
 */
public class 删除链表中的节点237 {
    public void deleteNode(Node node){
        if(node == null || node.next == null) return;
        Node p = node,pre = null;
        while(p.next != null){
            pre = p;
            p.val = p.next.val;
            p = p.next;
        }
        pre.next = null;
    }
}
