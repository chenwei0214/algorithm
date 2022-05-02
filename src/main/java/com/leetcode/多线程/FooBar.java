package com.leetcode.多线程;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode.多线程
 * Date: 2022/4/5 15:19
 * Created by: wei.chen
 */
public class FooBar {

    private final int n;
    private final Lock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private int flag = 1;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo() {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                if (flag != 1) {
                    condition1.await();
                }
                print("foo");
                flag = 2;
                condition2.signal();
            } catch (Exception e) {
                //do nothing
            } finally {
                lock.unlock();
            }
        }
    }

    public void bar() {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                if (flag != 2) {
                    condition2.await();
                }
                print("bar");
                flag = 1;
                condition1.signal();
            } catch (Exception e) {
                //do nothing
            } finally {
                lock.unlock();
            }
        }
    }

    public void print(String str) {
        System.out.print(str);
    }

    public static void main(String[] args) {
        FooBar fooBar = new FooBar(5);

        new Thread(new Runnable() {
            @Override
            public void run() {
                fooBar.foo();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                fooBar.bar();
            }
        }).start();
    }
}
