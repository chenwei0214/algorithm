package com.leetcode.树;

import java.util.Stack;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/22 11:02
 * Created by: wei.chen
 */
public class 验证二叉搜索树 {

    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        Integer pre = Integer.MIN_VALUE;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                if (pre >= node.val) return false;
                pre = node.val;
                node = node.right;
            }
        }
        return true;
    }
}
