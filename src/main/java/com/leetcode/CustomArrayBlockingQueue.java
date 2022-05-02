package com.leetcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/4/21 17:08
 * Created by: wei.chen
 */
public class CustomArrayBlockingQueue<E> {
    private int count;
    private final Object[] items;

    private int putIndex;
    private int takeIndex;

    private final ReentrantLock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public CustomArrayBlockingQueue(int capacity, boolean fair) {
        this.items = new Object[capacity];
        this.lock = new ReentrantLock(fair);
        this.notFull = lock.newCondition();
        this.notEmpty = lock.newCondition();
    }

    private static void checkNotNull(Object v) {
        if (v == null)
            throw new NullPointerException();
    }

    /**
     * 不会阻塞当前线程
     *
     * @param e
     * @return
     */
    public boolean offer(E e) {
        checkNotNull(e);
        lock.lock();
        try {
            if (count == items.length) {
                return false;
            } else {
                enqueue(e);
                return true;
            }
        } finally {
            lock.unlock();
        }
    }

    public void put(E e) throws InterruptedException {
        checkNotNull(e);
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            enqueue(e);
        } finally {
            lock.unlock();
        }
    }

    private void enqueue(E e) {
        items[putIndex] = e;
        if (++putIndex == items.length) {
            putIndex = 0;
        }
        count++;
        notEmpty.signal();
    }

    /**
     * 不会阻塞当前线程
     *
     * @return
     */
    public E poll() {
        lock.lock();
        try {
            return (count == 0) ? null : dequeue();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    @SuppressWarnings("unchecked")
    public E itemAt(int i) {
        return (E) items[i];
    }

    public E peek() {
        lock.lock();
        try {
            return itemAt(takeIndex);
        } finally {
            lock.unlock();
        }
    }

    private E dequeue() {
        @SuppressWarnings("unchecked")
        E e = (E) items[takeIndex];
        items[takeIndex] = null;
        if (++takeIndex == items.length) {
            takeIndex = 0;
        }
        count--;
        notFull.signal();
        return e;
    }

    public int size() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public static void main(String[] args) {
        CustomArrayBlockingQueue<Integer> queue = new CustomArrayBlockingQueue<>(10, false);
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int j = 0; j < 5; j++) {
                            queue.put(j);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!queue.isEmpty()) {
                        System.out.println(queue.take());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
