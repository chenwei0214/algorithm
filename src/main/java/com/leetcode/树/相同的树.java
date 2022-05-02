package com.leetcode.树;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/22 11:08
 * Created by: wei.chen
 */
public class 相同的树 {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
}
