package com.gengsc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * shichaogeng
 * 创建日期：2017/11/30
 * 创建时间: 21:36
 */
public class LockTemplete {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try{
            // do my work.....
        }finally{
            lock.unlock();
        }
    }

}
