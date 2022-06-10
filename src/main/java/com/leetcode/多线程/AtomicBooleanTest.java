package com.leetcode.多线程;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Project Name: algorithm
 * Package Name: com.leetcode.多线程
 * Date: 2022/5/9 17:30
 * Created by: wei.chen
 */
public class AtomicBooleanTest implements Runnable {

    private AtomicBoolean isTimeToUpdate = new AtomicBoolean(false);

    @Override
    public void run() {
        if(isTimeToUpdate.compareAndSet(false, true)){
            System.out.println(Thread.currentThread().getId()+"do something");
            isTimeToUpdate.set(false);
        }
    }
}
