package com.queue;

/**
 * Project Name: algorithm
 * Package Name: com.queue
 * Date: 2021/11/26 11:07
 * Created by: wei.chen
 */
public class CircularQueue {

    private String[] items;
    private int head;
    private int tail;
    private int n;

    public CircularQueue(int capacity) {
        this.n = capacity;
        this.items = new String[capacity];
    }

    public boolean enqueue(String val) {
        if ((tail + 1) % n == head) return false;
        items[tail] = val;
        tail = (tail + 1) % n;
        return true;
    }

    public String dequeue() {
        if (head == tail) return null;
        String item = items[head];
        head = (head + 1) % n;
        return item;
    }
}
