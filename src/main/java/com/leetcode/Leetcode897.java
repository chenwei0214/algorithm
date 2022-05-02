package com.leetcode;

import java.util.Stack;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2021/12/16 20:21
 * Created by: wei.chen
 */
public class Leetcode897 {

    public static void main(String[] args) {
        int[] nums={5,3,2,1,4,6,8,7,9};
        TreeNode head = null;
        for(int i= 0;i<nums.length;i++){
            head = addNode(head,nums[i]);
        }

        head=increasingBST(head);
        System.out.println("");
    }

    public static TreeNode increasingBST(TreeNode root) {
        if (root == null) return root;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode head = null;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                head = addNode(head, node.val);
                node = node.right;
            }
        }
        return head;
    }

    public static TreeNode addNode(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        TreeNode p = root;
        while (p != null) {
            if (p.val > val) {
                if (p.left == null) {
                    p.left = new TreeNode(val);
                    break;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    p.right = new TreeNode(val);
                    break;
                }
                p = p.right;
            }
        }
        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
