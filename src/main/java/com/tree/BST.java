package com.tree;

import java.util.Comparator;

/**
 * Project Name: algorithm
 * Package Name: com.root
 * Date: 2021/11/30 10:29
 * Created by: wei.chen
 */
public class BST<T> extends BinaryTree<T> {

    private Comparator<T> comparator;

    public BST() {
        this(null);
    }

    public BST(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private int compare(T o1, T o2) {
        if (comparator != null) {
            return comparator.compare(o1, o2);
        }
        return ((Comparable<T>) o1).compareTo(o2);
    }

    public void add(T val) {
        // 添加第一个节点
        if (root == null) {
            root = createNode(val, null);
            size++;

            // 新添加节点之后的处理
            afterAdd(root);
            return;
        }

        // 添加的不是第一个节点
        // 找到父节点
        Node<T> parent = root;
        Node<T> node = root;
        int cmp = 0;
        do {
            cmp = compare(val, node.val);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else { // 相等
                node.val = val;
                return;
            }
        } while (node != null);

        // 看看插入到父节点的哪个位置
        Node<T> newNode = createNode(val, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

        // 新添加节点之后的处理
        afterAdd(newNode);
    }

    private void remove(Node<T> node) {
        if (node == null) return;

        size--;

        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<T> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.val = s.val;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<T> replacement = node.left != null ? node.left : node.right;

        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }

            // 删除节点之后的处理
            afterRemove(node);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            root = null;

            // 删除节点之后的处理
            afterRemove(node);
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }

            // 删除节点之后的处理
            afterRemove(node);
        }
    }

    /**
     * 添加node之后的调整
     *
     * @param node 新添加的节点
     */
    protected void afterAdd(Node<T> node) {
    }

    /**
     * 删除node之后的调整
     *
     * @param node 被删除的节点
     */
    protected void afterRemove(Node<T> node) {
    }

    public boolean remove(T val) {
        //要删除的节点
        Node<T> p = root;
        //要删除节点的父节点
        Node<T> pp = null;

        //找到要删除的节点
        int cmp = compare(p.val, val);
        while (p != null && cmp != 0) {
            pp = p;
            if (cmp > 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        //没有找到要删除的节点
        if (p == null) return false;

        //要删除的节点包含了两个子节点
        if (p.hasTwoChildren()) {
            //找到右子树最小的节点
            Node<T> minP = p.right;
            //右子树最小的节点的父节点
            Node<T> minPP = p;

            //找到右子树最小的节点
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }

            //将右子树最小的节点值赋值给要删除的节点
            p.val = minP.val;

            //变成删除minp节点
            p = minP;
            pp = minPP;
        }

        // 删除节点是叶子节点或者仅有一个子节点
        Node<T> child;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        //没有父节点表示要删除的是根节点
        if (pp == null) {
            root = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }
        if (child != null) {
            child.parent = pp;
        }

        // 删除节点之后的处理
        afterRemove(p);
        return true;
    }

    public Node<T> find(T val) {
        Node<T> p = root;
        while (p != null) {
            int cmp = compare(p.val, val);
            if (cmp == 0) {
                return p;
            } else if (cmp > 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return null;
    }

    public Node<T> findMin() {
        Node<T> p = root;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    public Node<T> findMax() {
        Node<T> p = root;
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

}
