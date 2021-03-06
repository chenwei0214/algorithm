package com.leetcode.树;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/4/1 15:20
 * Created by: wei.chen
 */
public class 树的子结构 {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    public boolean recur(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null || A.val != B.val) return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }
}
