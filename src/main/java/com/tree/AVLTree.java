package com.tree;

import java.util.Comparator;

/**
 * Project Name: algorithm
 * Package Name: com.tree
 * Date: 2021/12/9 13:40
 * Created by: wei.chen
 */
public class AVLTree<T> extends BST<T> {

    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<T> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<T> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalance(node);
                // 整棵树恢复平衡
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<T> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalance(node);
            }
        }
    }

    @Override
    protected Node<T> createNode(T val, Node<T> parent) {
        return new AVLNode<>(val, parent);
    }

    /**
     * 恢复平衡
     * @param grand 高度最低的那个不平衡节点
     */
    @SuppressWarnings("unused")
    private void rebalance2(Node<T> grand) {
        Node<T> parent = ((AVLNode<T>)grand).tallerChild();
        Node<T> node = ((AVLNode<T>)parent).tallerChild();
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                rotateRight(grand);
            } else { // LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else { // R
            if (node.isLeftChild()) { // RL
                rotateRight(parent);
                rotateLeft(grand);
            } else { // RR
                rotateLeft(grand);
            }
        }
    }
    /**
     * 恢复平衡
     * @param grand 高度最低的那个不平衡节点
     */
    private void rebalance(Node<T> grand) {
        Node<T> parent = ((AVLNode<T>)grand).tallerChild();
        Node<T> node = ((AVLNode<T>)parent).tallerChild();

        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                rotate(grand, node, node.right, parent, parent.right, grand);
            } else { // LR
                rotate(grand, parent, node.left, node, node.right, grand);
            }
        } else { // R
            if (node.isLeftChild()) { // RL
                rotate(grand, grand, node.left, node, node.right, parent);
            } else { // RR
                rotate(grand, grand, parent.left, parent, node.left, node);
            }
        }
    }

    private void rotate(
            Node<T> r, // 子树的根节点
            Node<T> b, Node<T> c,
            Node<T> d,
            Node<T> e, Node<T> f) {
        // 让d成为这棵子树的根节点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        //b-c
        b.right = c;
        if (c != null) {
            c.parent = b;
        }
        updateHeight(b);

        // e-f
        f.left = e;
        if (e != null) {
            e.parent = f;
        }
        updateHeight(f);

        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
        updateHeight(d);
    }

    private void rotateLeft(Node<T> grand) {
        Node<T> parent = grand.right;
        Node<T> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    private void rotateRight(Node<T> grand) {
        Node<T> parent = grand.left;
        Node<T> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    private void afterRotate(Node<T> grand, Node<T> parent, Node<T> child) {
        // 让parent称为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // grand是root节点
            root = parent;
        }

        // 更新child的parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand的parent
        grand.parent = parent;

        // 更新高度
        updateHeight(grand);
        updateHeight(parent);
    }


    private boolean isBalanced(Node<T> node) {
        return Math.abs(((AVLNode<T>) node).balanceFactor()) <= 1;
    }

    private void updateHeight(Node<T> node) {
        ((AVLNode<T>) node).updateHeight();
    }

    private static class AVLNode<T> extends Node<T> {
        int height = 1;

        public AVLNode(T val) {
            super(val);
        }

        public AVLNode(T val, Node<T> parent) {
            super(val, parent);
        }

        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<T>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<T>) right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<T>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<T>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<T> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<T>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<T>) right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            return isLeftChild() ? left : right;
        }

    }
}
