package com.leetcode.多线程;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode.多线程
 * Date: 2022/4/1 17:32
 * Created by: wei.chen
 */
public class 按序打印 {

    private final Lock lock = new ReentrantLock();

    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final Condition condition3 = lock.newCondition();

    private int flag = 1;

    public void first() throws InterruptedException {
        lock.lock();
        if (flag != 1) {
            condition1.await();
        }
        System.out.println("first");
        flag = 2;
        condition2.signal();
        lock.unlock();

    }

    public void second() throws InterruptedException {
        lock.lock();
        if (flag != 2) {
            condition2.await();
        }
        System.out.println("second");
        flag = 3;
        condition3.signal();
        lock.unlock();
    }

    public void third() throws InterruptedException {
        lock.lock();
        if (flag != 3) {
            condition3.await();
        }
        System.out.println("third");
        flag = 1;
        condition1.signal();
        lock.unlock();
    }

    public static void main(String[] args) {
        按序打印 obj = new 按序打印();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    obj.first();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    obj.second();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    obj.third();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
