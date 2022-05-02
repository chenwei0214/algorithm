package com.leetcode.多线程;

import java.util.concurrent.*;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode.多线程
 * Date: 2022/4/19 14:58
 * Created by: wei.chen
 */
public class ScheduledTaskTest {

    private volatile String config = "open";

    private ScheduledTaskTest() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r, "PullSupplierLandStatisticsConfig");
                t.setDaemon(true);
                return t;
            }
        });

        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                System.out.println(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getThreadGroup().getName());
                config = System.currentTimeMillis() + "";
            }
        }, 1, 5, TimeUnit.SECONDS);
    }

    public void close() {
        this.config = "close";
    }

    public void open() {
        this.config = "open";
    }

    public void printer() {
        if ("open".equals(this.config)) {
            System.out.println("hello");
        }else {
//            System.out.println("world");
        }
    }

    private static final ScheduledTaskTest instance = new ScheduledTaskTest();

    public static ScheduledTaskTest getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        ScheduledTaskTest.getInstance().printer();
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
        }

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ScheduledTaskTest.getInstance().close();

    }

}
