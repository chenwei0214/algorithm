package com.leetcode.树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/23 11:03
 * Created by: wei.chen
 */
public class 二叉树展开为链表 {

    public void flatten(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        List<TreeNode> nodes = new ArrayList<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                nodes.add(node);
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }

        for (int i = 1; i < nodes.size(); i++) {
            TreeNode pre = nodes.get(i-1);
            TreeNode cur = nodes.get(i);
            pre.left = null;
            pre.right = cur;
        }
    }
}
