package com.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Project Name: algorithm
 * Package Name: com.tree
 * Date: 2021/12/8 16:40
 * Created by: wei.chen
 */
public class BinaryTree<T> {

    protected Node<T> root;
    protected int size;

    public int getHeightWithRecurve(Node<T> node) {
        if (node == null) return 0;
        //max(左子树高度+右子树高度)+1
        int lheight = getHeightWithRecurve(node.left);
        int rheight = getHeightWithRecurve(node.right);
        return Math.max(lheight, rheight) + 1;
    }

    public int getHeightWithLevelOrderTraveral() {
        if (root == null) return 0;
        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);
        int levelSize = 1;
        int height = 0;
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            levelSize--;
            //将下一层的数据添加到队列
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

//    public int getHeightWithLevelOrderTraveral() {
//        if (root == null) return 0;
//        Queue<Node> queue = new LinkedList<>();
//        queue.offer(root);
//        int height = 0;
//        while (!queue.isEmpty()) {
//            int currentLevelSize = queue.size();
//            height++;
//            for (int i = 0; i < currentLevelSize; i++) {
//                Node node = queue.poll();
//                //将下一层的数据添加到队列
//                if (node.left != null) {
//                    queue.offer(node.left);
//                }
//                if (node.right != null) {
//                    queue.offer(node.right);
//                }
//            }
//        }
//        return height;
//    }

    public Node<T> predecessor(Node<T> node) {
        if (node == null) return null;
        //如果左子树不为空，前驱节点是左子树中最大的节点
        if (node.left != null) {
            Node<T> p = node.left;
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }
        //从父节点开始查找，找比自己小的父节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        return node.parent;
    }

    public Node<T> successor(Node<T> node) {
        if (node == null) return null;
        //如果右子树不为空，后继节点是右子树中最小的节点
        if (node.right != null) {
            Node<T> p = node.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    public void preOrderTraveralWithRecurve(Visitor<T> visitor) {
        if (root == null || visitor == null) return;
        preOrderTraveralWithRecurve(root, visitor);
    }

    /**
     * 前序遍历
     */
    public void preOrderTraveralWithRecurve(Node<T> node, Visitor<T> visitor) {
        if (node == null || visitor.stop) return;
        visitor.stop = visitor.visite(node.val);
        preOrderTraveralWithRecurve(node.left, visitor);
        preOrderTraveralWithRecurve(node.right, visitor);
    }

    public void inOrderTraveralWithRecurve(Visitor<T> visitor) {
        if (root == null || visitor == null) return;
        inOrderTraveralWithRecurve(root, visitor);
    }

    //中序遍历
    public void inOrderTraveralWithRecurve(Node<T> node, Visitor<T> visitor) {
        if (node == null || visitor.stop) return;
        inOrderTraveralWithRecurve(node.left, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visite(node.val);
        inOrderTraveralWithRecurve(node.right, visitor);
    }

    public void postOrderTraveralWithRecurve(Visitor<T> visitor) {
        if (root == null || visitor == null) return;
        postOrderTraveralWithRecurve(root, visitor);
    }

    //后续遍历
    public void postOrderTraveralWithRecurve(Node<T> node, Visitor<T> visitor) {
        if (node == null || visitor.stop) return;
        postOrderTraveralWithRecurve(node.left, visitor);
        postOrderTraveralWithRecurve(node.right, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visite(node.val);
    }


    public void preOrderTraveralWithStack(Visitor<T> visitor) {
        if (root == null || visitor == null) return;
        Stack<Node<T>> stack = new Stack<>();
        Node<T> rootNode = root;
        while (rootNode != null || !stack.isEmpty()) {
            //迭代访问节点的左孩子，并入栈
            while (rootNode != null) {
                if (visitor.stop) return;
                visitor.stop = visitor.visite(rootNode.val);
                stack.push(rootNode);
                rootNode = rootNode.left;
            }
            //如果节点没有左孩子，则弹出栈顶节点，访问节点右孩子
            if (!stack.isEmpty()) {
                rootNode = stack.pop();
                rootNode = rootNode.right;
            }
        }
    }

    public void inOrderTraveralWithStack(Visitor<T> visitor) {
        if (root == null || visitor == null) return;
        Stack<Node<T>> stack = new Stack<>();
        Node<T> node = root;
        while (node != null || !stack.isEmpty()) {
            //迭代访问节点的左孩子，并入栈
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            //如果节点没有左孩子，则弹出栈顶节点，访问节点右孩子
            if (!stack.isEmpty()) {
                node = stack.pop();
                if (visitor.stop) return;
                visitor.stop = visitor.visite(node.val);
                node = node.right;
            }
        }
    }

    public void postOrderTraveralWithStack(Visitor<T> visitor) {
        if (root == null || visitor == null) return;
        Stack<Node<T>> stack = new Stack<>();
        Node<T> node = root;
        //标记每次遍历最后一次访问的节点
        Node<T> lastVisit = null;
        while (node != null || !stack.isEmpty()) {
            //迭代访问节点的左孩子，并入栈
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            //栈不为空
            if (!stack.isEmpty()) {
                //出栈
                node = stack.pop();
                if (node.right == null || node.right == lastVisit) {
                    if (visitor.stop) return;
                    visitor.stop = visitor.visite(node.val);
                    lastVisit = node;
                    node = null;
                } else {
                    stack.push(node);
                    node = node.right;
                }
            }
        }
    }

    /**
     * 层序遍历
     */
    public void levelOrderTraveral(Visitor<T> visitor) {
        if (root == null) return;
        LinkedList<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            if (visitor.stop) return;
            visitor.stop = visitor.visite(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public void buildTreeWithPreOrderAndInOrder(T[] preOrder, T[] inOrder) {
        if (preOrder == null || preOrder.length == 0) return;
        if (inOrder == null || inOrder.length == 0) return;
        root = buildTreeWithPreOrderAndInOrder(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1, null, size);
    }

    private Node<T> buildTreeWithPreOrderAndInOrder(T[] preOrder, int pstart, int pend, T[] inOrder, int istart, int iend, Node<T> parent, int size) {
        if (pstart > pend || istart > iend) return null;

        Node<T> root = new Node<>(preOrder[pstart], parent);
        size++;
        //在inOrder中找到根节点所在下标
        int rootIndex = istart;
        while (rootIndex <= iend && inOrder[rootIndex] != preOrder[pstart]) {
            rootIndex++;
        }
        int leftCount = rootIndex - istart;
        root.left = buildTreeWithPreOrderAndInOrder(preOrder, pstart + 1, pstart + leftCount, inOrder, istart, rootIndex - 1, root, size);
        root.right = buildTreeWithPreOrderAndInOrder(preOrder, pstart + leftCount + 1, pend, inOrder, rootIndex + 1, iend, root, size);
        return root;
    }

    public void buildTreeWithPostOrderAndInOrder(T[] postOrder, T[] inOrder) {
        if (postOrder == null || postOrder.length == 0) return;
        if (inOrder == null || inOrder.length == 0) return;
        root = buildTreeWithPostOrderAndInOrder(postOrder, 0, postOrder.length - 1, inOrder, 0, inOrder.length - 1, null, size);
    }

    private Node<T> buildTreeWithPostOrderAndInOrder(T[] postOrder, int pstart, int pend, T[] inOrder, int istart, int iend, Node<T> parent, int size) {
        if (pstart > pend || istart > iend) return null;

        Node<T> root = new Node<>(postOrder[pend], parent);
        size++;
        //在inOrder中找到根节点所在下标
        int rootIndex = istart;
        while (rootIndex <= iend && inOrder[rootIndex] != postOrder[pend]) {
            rootIndex++;
        }
        int leftCount = rootIndex - istart;

        root.left = buildTreeWithPostOrderAndInOrder(postOrder, pstart, pstart + leftCount - 1, inOrder, istart, rootIndex - 1, root, size);
        root.right = buildTreeWithPostOrderAndInOrder(postOrder, pstart + leftCount, pend - 1, inOrder, rootIndex + 1, iend, root, size);
        return root;
    }

    public boolean isComplete() {
        if (root == null) return false;

        Queue<Node<T>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }
            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                return false;
            }
            if (node.right != null) {
                queue.offer(node.right);
            } else {
                leaf = true;
            }
        }
        return true;
    }

    protected Node<T> createNode(T val, Node<T> parent) {
        return new Node<>(val, parent);
    }

    public Node<T> root() {
        return root;
    }

    public int size() {
        return size;
    }

    public static abstract class Visitor<T> {
        boolean stop;

        abstract boolean visite(T val);
    }


    protected static class Node<T> {
        T val;
        Node<T> left;
        Node<T> right;
        Node<T> parent;

        public Node(T val) {
            this.val = val;
        }

        public Node(T val, Node<T> parent) {
            this.val = val;
            this.parent = parent;
        }

        public Node(T val, Node<T> left, Node<T> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public Node(T val, Node<T> left, Node<T> right, Node<T> parent) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public Node<T> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }

            return null;
        }
    }
}
