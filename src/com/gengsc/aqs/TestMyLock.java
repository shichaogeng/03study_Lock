package com.gengsc.aqs;

import com.gengsc.SleepUtils;

import java.util.concurrent.locks.Lock;

/**
 * shichaogeng
 * 创建日期：2017/11/20
 * 创建时间: 17:42
 */
public class TestMyLock {

    public void test() {
//        final Lock lock = new TwoThreadsLock();
        final Lock lock = new SingleLock();
//        final Lock lock = new DoubleLock();
        class Worker extends Thread {
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                    SleepUtils.second(2);
                }
            }
        }
        // 启动10个子线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
//            w.setDaemon(true);
            w.start();
        }
        // 主线程每隔1秒换行
        for (int i = 0; i < 10; i++) {
                SleepUtils.second(1);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TestMyLock testMyLock = new TestMyLock();
        testMyLock.test();
    }
}
