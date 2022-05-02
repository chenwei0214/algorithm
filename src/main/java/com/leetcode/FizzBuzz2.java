package com.leetcode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode
 * Date: 2021/12/14 20:21
 * Created by: wei.chen
 */
public class FizzBuzz2 {

    private int n;

    public FizzBuzz2(int n) {
        this.n = n;
    }

    private int x = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public static void main(String[] args) {
        FizzBuzz2 fizzBuzz = new FizzBuzz2(1);

        IntConsumer printer = new IntConsumer();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fizzBuzz.fizz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print("fizz" + "\t");
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
                    fizzBuzz.buzz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print("buzz" + "\t");
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
                    fizzBuzz.fizzbuzz(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print("fizzbuzz" + "\t");
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
                    fizzBuzz.number(printer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (x <= n) {
            try {
                lock.lock();
                if (x % 3 == 0 && x % 5 != 0) {
                    printFizz.run();
                    x++;
                    condition.signalAll();
                } else {
                    condition.await();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (x <= n) {
            try {
                lock.lock();
                if (x % 3 != 0 && x % 5 == 0) {
                    printBuzz.run();
                    x++;
                    condition.signalAll();
                } else {
                    condition.await();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (x <= n) {
            try {
                lock.lock();
                if (x % 3 == 0 && x % 5 == 0) {
                    printFizzBuzz.run();
                    x++;
                    condition.signalAll();
                } else {
                    condition.await();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (x <= n) {
            try {
                lock.lock();
                if (x % 3 != 0 && x % 5 != 0) {
                    printNumber.accept(x);
                    x++;
                    condition.signalAll();
                } else {
                    condition.await();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static class IntConsumer {
        void accept(int x) {
            System.out.print(x + "\t");
        }
    }
}
