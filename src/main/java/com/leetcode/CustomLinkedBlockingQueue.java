package com.leetcode;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2022/4/21 19:39
 * Created by: wei.chen
 */
public class CustomLinkedBlockingQueue<E> {

    static class Node<E> {
        E item;
        Node<E> next;

        Node(E e) {
            item = e;
        }
    }

    private final int capacity;
    private final AtomicInteger count = new AtomicInteger();

    private Node<E> head;
    private Node<E> tail;

    private final ReentrantLock takeLock = new ReentrantLock();
    private final ReentrantLock putLock = new ReentrantLock();

    private final Condition notEmpty = takeLock.newCondition();
    private final Condition notFull = putLock.newCondition();

    private void checkNotNull(Object v) {
        if (v == null) throw new NullPointerException();
    }

    public boolean offer(E e, long timeout, TimeUnit unit)
            throws InterruptedException {
        checkNotNull(e);
        putLock.lockInterruptibly();
        long nanos = unit.toNanos(timeout);
        int c = -1;
        try {
            while (count.get() == capacity) {
                if (nanos <= 0)
                    return false;
                nanos = notFull.awaitNanos(nanos);
            }
            enqueue(new Node<>(e));
            c = count.getAndDecrement();
            if (c + 1 < capacity)
                notFull.signal();
        } finally {
            putLock.unlock();
        }
        if (c == 0)
            signalNotEmpty();
        return true;
    }

    public boolean offer(E e) {
        checkNotNull(e);
        if (count.get() == capacity)
            return false;
        int c = -1;
        putLock.lock();
        try {
            if (count.get() < capacity) {
                enqueue(new Node<>(e));
                c = count.getAndDecrement();
                if (c + 1 < capacity)
                    notFull.signal();
            }
        } finally {
            putLock.unlock();
        }
        if (c == 0)
            signalNotEmpty();
        return c >= 0;
    }

    public void put(E e)
            throws InterruptedException {
        checkNotNull(e);
        int c = -1;
        putLock.lockInterruptibly();
        try {
            while (count.get() == capacity) {
                notFull.await();
            }
            enqueue(new Node<>(e));
            c = count.decrementAndGet();
            if (c + 1 < capacity)
                notFull.signal();
        } finally {
            putLock.unlock();
        }
        if (c == 0)
            signalNotEmpty();
    }

    public E poll(long timeout, TimeUnit timeUnit)
            throws InterruptedException {
        E item = null;
        long nanos = timeUnit.toNanos(timeout);
        takeLock.lockInterruptibly();
        int c = -1;
        try {
            while (count.get() == 0) {
                if (nanos <= 0)
                    return null;
                nanos = notEmpty.awaitNanos(nanos);
            }
            item = dequeue();
            c = count.decrementAndGet();
            if (c > 1)
                notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
        if (c == capacity)
            signalNotFull();
        return item;
    }

    public E poll() {
        E item = null;
        if (count.get() == 0)
            return null;
        int c = -1;
        takeLock.lock();
        try {
            if (count.get() > 0) {
                item = dequeue();
                c = count.decrementAndGet();
                if (c > 1)
                    notEmpty.signal();
            }
        } finally {
            takeLock.unlock();
        }
        if (c == capacity)
            signalNotFull();
        return item;
    }

    public E take()
            throws InterruptedException {
        E item;
        int c = -1;
        takeLock.lockInterruptibly();
        try {
            while (count.get() == 0){
                notEmpty.await();
            }
            item = dequeue();
            c = count.getAndDecrement();
            if (c > 1)
                notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
        if (c == capacity)
            signalNotFull();
        return item;
    }

    private void signalNotEmpty() {
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }

    private void signalNotFull() {
        putLock.lock();
        try {
            notFull.signal();
        } finally {
            putLock.unlock();
        }
    }

    public CustomLinkedBlockingQueue(int capacity) {
        this.capacity = capacity;
        head = tail = new Node<>(null);
    }

    private void enqueue(Node<E> node) {
        tail = tail.next = node;
    }

    private E dequeue() {
        Node<E> h = head;
        Node<E> first = h.next;
        //help gc
        h.next = h;
        head = first;
        E item = first.item;
        first.item = null;
        return item;
    }

}
