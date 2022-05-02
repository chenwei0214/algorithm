package com.leetcode.树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/4/8 15:40
 * Created by: wei.chen
 */
public class N叉树的层序遍历 {

    static class Node {
        int val;
        List<Node> children;

        public Node() {
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    public List<List<Integer>> levelOrder01(Node root) {
        if (root == null) return new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int levelSize = 1;
        List<List<Integer>> resultList = new ArrayList<>();
        List<Integer> levelList = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            levelSize--;
            levelList.add(node.val);
            if (node.children != null
                    && node.children.size() > 0) {
                queue.addAll(node.children);
            }
            if (levelSize == 0) {
                levelSize = queue.size();
                resultList.add(levelList);
                levelList = new ArrayList<>();
            }
        }
        return resultList;
    }

    public List<List<Integer>> levelOrder02(Node root) {
        if (root == null) return new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> resultList = new ArrayList<>();
        while(!queue.isEmpty()){
            List<Integer> levelList = new ArrayList<>();
            int levelSize = queue.size();
            for(int i = 0;i < levelSize; i++){
                Node node = queue.poll();
                levelList.add(node.val);
                if(node.children != null && node.children.size() > 0){
                    queue.addAll(node.children);
                }
            }
            resultList.add(levelList);
        }
        return resultList;
    }
}
