package com.leetcode.树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/21 20:06
 * Created by: wei.chen
 */
public class 二叉树层序遍历 {

    public void levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }
}
