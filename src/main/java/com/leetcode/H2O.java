package com.leetcode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2021/12/15 13:36
 * Created by: wei.chen
 */
public class H2O {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public H2O() {
    }

    public static void main(String[] args) {
        H2O h2o = new H2O();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    h2o.hydrogen(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print("H");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    h2o.oxygen(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print("O");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            releaseHydrogen.run();
            //判断打印H的个数
            if (number == 1) {
                number++;
            } else if (number == 2) {
                //打印O
                number = 0;
            }
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            releaseOxygen.run();
            //打印H
            number = 1;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
