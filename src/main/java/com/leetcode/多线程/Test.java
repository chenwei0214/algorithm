package com.leetcode.多线程;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode.多线程
 * Date: 2022/4/7 10:26
 * Created by: wei.chen
 * Desc:两个线程交替打印1-100的整数
 */
public class Test {
    private int num;
    private final Lock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();

    public void printOdd() {
        while (num < 100) {
            lock.lock();
            try {
                if (num % 2 == 0) {
                    condition1.await();
                }
                System.out.println(Thread.currentThread().getName() + ":" + num);
                num++;
                condition2.signal();
            } catch (Exception e) {
                //do nothing
            } finally {
                lock.unlock();
            }
        }
    }

    public void printEven() {
        while (num < 100) {
            lock.lock();
            try {
                if (num % 2 != 0) {
                    condition2.await();
                }
                System.out.println(Thread.currentThread().getName() + ":" + num);
                num++;
                condition1.signal();
            } catch (Exception e) {
                //do nothing
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Test test = new Test();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.printOdd();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.printEven();
            }
        }).start();
    }

}
