package com.leetcode.缓存;

import java.util.HashMap;
import java.util.Map;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/3/22 11:29
 * Created by: wei.chen
 */
public class LRUCacheV1 {

    public static class CacheNode {
        Integer key;
        Integer value;
        CacheNode prev;
        CacheNode next;

        public CacheNode() {
        }

        public CacheNode(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private final Map<Integer, CacheNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private CacheNode head, tail;

    public LRUCacheV1(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new CacheNode();
        tail = new CacheNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        CacheNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        CacheNode node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            CacheNode newNode = new CacheNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                CacheNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        } else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(CacheNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(CacheNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(CacheNode node) {
        removeNode(node);
        addToHead(node);
    }

    private CacheNode removeTail() {
        CacheNode res = tail.prev;
        removeNode(res);
        return res;
    }

}
