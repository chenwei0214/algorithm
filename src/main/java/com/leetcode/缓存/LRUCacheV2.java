package com.leetcode.缓存;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode.缓存
 * Date: 2022/4/25 15:18
 * Created by: wei.chen
 */
public class LRUCacheV2<T> {

    static class Node<T> {
        final String key;
        T val;
        final long writeTime = System.nanoTime();
        Node(String key, T val) {
            this.key = key;
            this.val = val;
        }

        public String getKey() {
            return key;
        }

        public T getVal() {
            return val;
        }

        public void setVal(T val) {
            this.val = val;
        }

        public long getExpireTime() {
            return System.nanoTime() - writeTime;
        }

        public long getWriteTime() {
            return writeTime;
        }
    }

    private final int capacity;
    private final long expire;
    private final TimeUnit timeUnit;
    private final Map<String, Node<T>> store = new ConcurrentHashMap<>();

    public LRUCacheV2(int capacity, int expire, TimeUnit timeUnit) {
        this.capacity = capacity;
        this.expire = expire;
        this.timeUnit = timeUnit;

        ScheduledExecutorService lruCacheExpireCheckTask = Executors.newScheduledThreadPool(1, r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName("LRUCache Expire Check");
            return thread;
        });

        lruCacheExpireCheckTask.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Map<String, Node<T>> cache = LRUCacheV2.this.store;
                if (cache.isEmpty()) return;
                for (Map.Entry<String, Node<T>> entry : cache.entrySet()) {
                    if (!isExpire(entry.getValue())) {
                        continue;
                    }
                    cache.remove(entry.getKey());
                }
                System.out.println("clear cache data");
            }
        }, 5, 5, TimeUnit.SECONDS);
    }

    private void checkNotNull(Object v) {
        if (v == null) throw new NullPointerException();
    }

    public boolean put(String key, T val) {
        checkNotNull(key);
        checkNotNull(val);
        Node<T> node = new Node<>(key, val);
        if (store.containsKey(key)) {
            store.put(key, node);
            return true;
        } else {
            if (store.size() == capacity) return false;
            store.put(key, node);
            return true;
        }
    }

    public T get(String key) {
        checkNotNull(key);
        Node<T> node = store.get(key);
        if (node == null) return null;
        if (isExpire(node)) {
            store.remove(key);
            return null;
        }
        return node.getVal();
    }

    public int size(){
        return store.size();
    }

    private boolean isExpire(Node<T> node) {
        return node.getExpireTime() > timeUnit.toNanos(expire);
    }
}
