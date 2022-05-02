package com.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2021/12/13 18:45
 * Created by: wei.chen
 */

public class ZeroEvenOdd1 {
    private int n;
    private Lock lock = new ReentrantLock();
    private Condition zeroCondition = lock.newCondition();
    private Condition evenCondition = lock.newCondition();
    private Condition oddCondition = lock.newCondition();
    private volatile int flag = 0;


    public static void main(String[] args) {
        ZeroEvenOdd1 zeroEvenOdd = new ZeroEvenOdd1(2);

        IntConsumer printer = new IntConsumer();

        Thread zeroThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.zero(printer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread evenThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.even(printer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread oddThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.odd(printer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        zeroThread.start();
        evenThread.start();
        oddThread.start();



        HashMap<String, Integer> objectObjectHashMap = new HashMap<>();
        for(Map.Entry<String,Integer> entry:objectObjectHashMap.entrySet()){
            System.out.println(entry.getKey());
        }
    }


    public ZeroEvenOdd1(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        try {
            lock.lock();
            for (int i = 1; i <= n; i++) {
                if (flag != 0) {
                    zeroCondition.await();
                }
                printNumber.accept(0);
                if (i % 2 == 0) {
                    flag = 1;
                    evenCondition.signal();
                } else {
                    flag = 2;
                    oddCondition.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        try {
            lock.lock();
            for (int i = 2; i <= n; i += 2) {
                if (flag != 1) {
                    evenCondition.await();
                }
                printNumber.accept(i);
                flag = 0;
                zeroCondition.signal();
            }
        } finally {
            lock.unlock();
        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        try {
            lock.lock();
            for (int i = 1; i <= n; i += 2) {
                if (flag != 2) {
                    oddCondition.await();
                }
                printNumber.accept(i);
                flag = 0;
                zeroCondition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public static class IntConsumer {
        void accept(int x) {
            System.out.print(x + "\t");
        }
    }
}
