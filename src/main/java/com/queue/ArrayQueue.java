package com.queue;

/**
 * Project Name: algorithm
 * Package Name: com.queue
 * Date: 2021/11/26 10:40
 * Created by: wei.chen
 */
public class ArrayQueue {
    private String[] items;
    private int head = 0;
    private int tail = 0;
    private int n;

    public ArrayQueue(int capacity) {
        this.n = capacity;
        items = new String[capacity];
    }

    public Boolean enqueue(String val) {
        if (this.tail == this.n) return false;
        items[this.tail] = val;
        this.tail++;
        return true;
    }

    public String dequeue() {
        if (this.head == this.tail) {
            System.out.println("queue empty");
            return null;
        }
        String val = items[this.head];
        this.head++;
        return val;
    }


}
