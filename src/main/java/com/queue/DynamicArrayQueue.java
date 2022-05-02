package com.queue;

/**
 * Project Name: algorithm
 * Package Name: com.queue
 * Date: 2021/11/26 11:16
 * Created by: wei.chen
 */
public class DynamicArrayQueue {
    private int n;
    private String[] items;
    private int head;
    private int tail;

    public DynamicArrayQueue(int capacity) {
        this.n = capacity;
        this.items = new String[capacity];
    }

    public boolean enqueue(String val) {
        if (tail == n) {
            if (head == 0) return false;
            for (int i = head; i < tail; i++) {
                items[i - head] = items[i];
            }
            tail = tail - head;
            head = 0;
        }
        items[tail] = val;
        tail++;
        return true;
    }

    public String dequeue() {
        if (head == tail) return null;
        String item = items[head];
        head++;
        return item;
    }
}
