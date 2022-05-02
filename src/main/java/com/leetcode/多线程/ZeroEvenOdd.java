package com.leetcode.多线程;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode.多线程
 * Date: 2022/4/5 13:33
 * Created by: wei.chen
 * Desc: 打印零与奇偶数
 */
public class ZeroEvenOdd {

    private final int n;
    private final Lock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final Condition condition3 = lock.newCondition();

    private int flag = 0;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    public void zero(IntConsumer consumer) {
        for (int i = 1; i <= n; i++) {
            lock.lock();
            try {
                if (flag != 0) {
                    condition1.await();
                }
                consumer.accept(0);
                if (i % 2 == 0) {
                    flag = 2;
                    condition2.signal();
                } else {
                    flag = 1;
                    condition3.signal();
                }
            } catch (Exception e) {
                //do nothing
            } finally {
                lock.unlock();
            }
        }
    }

    public void even(IntConsumer consumer) {
        for (int i = 2; i <= n; i += 2) {
            lock.lock();
            try {
                if (flag != 2) {
                    condition2.await();
                }
                consumer.accept(i);
                flag = 0;
                condition1.signal();
            } catch (Exception e) {
                //do nothing
            } finally {
                lock.unlock();
            }
        }
    }

    public void odd(IntConsumer consumer) {
        for (int i = 1; i <= n; i += 2) {
            lock.lock();
            try {
                if (flag != 1) {
                    condition3.await();
                }
                consumer.accept(i);
                flag = 0;
                condition1.signal();
            } catch (Exception e) {
                //do nothing
            } finally {
                lock.unlock();
            }
        }
    }


    static class IntConsumer {
        public void accept(int n) {
            System.out.println(n);
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(10);
        ZeroEvenOdd.IntConsumer consumer = new ZeroEvenOdd.IntConsumer();

        new Thread(new Runnable() {
            @Override
            public void run() {
                zeroEvenOdd.zero(consumer);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                zeroEvenOdd.even(consumer);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                zeroEvenOdd.odd(consumer);
            }
        }).start();
    }
}
