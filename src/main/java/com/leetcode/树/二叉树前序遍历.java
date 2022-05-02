package com.leetcode.树;

import java.util.Stack;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/21 20:16
 * Created by: wei.chen
 */
public class 二叉树前序遍历 {

    public void preOrder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                System.out.println(node.val);
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }
    }

    public void preOrder01(TreeNode root) {
        if (root == null) return;
        System.out.println(root.val);
        preOrder01(root.left);
        preOrder01(root.right);
    }
}
