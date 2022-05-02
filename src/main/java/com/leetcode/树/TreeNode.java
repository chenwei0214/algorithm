package com.leetcode.树;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/21 20:06
 * Created by: wei.chen
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(){}

    public TreeNode(int val){
        this.val = val;
    }

    public TreeNode(int val,TreeNode left,TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
