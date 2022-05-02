package com.leetcode.多线程;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode.多线程
 * Date: 2022/4/2 10:51
 * Created by: wei.chen
 * Desc：100个线程向账户里存1元钱，打印结果
 */
public class Account01 {

    private double balance;

    public Account01(double balance) {
        this.balance = balance;
    }

    private final Lock lock = new ReentrantLock();

    public void deposit(double money) {
        lock.lock();
        try {
            balance = balance + money;
        } catch (Exception e) {
            //do nothing
        } finally {
            lock.unlock();
        }
    }

    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        Account01 account = new Account01(0);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    account.deposit(1);
                }
            });
        }

        executorService.shutdown();
        while(executorService.isTerminated()){}

        System.out.println(account.getBalance());
    }
}
