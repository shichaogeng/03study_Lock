package com.gengsc.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description
 * @Author shichaogeng
 * @Create 2017-12-08 10:17
 */
public class DoubleLock implements Lock{

    private static Sync sync = new Sync(2);

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    static class Sync extends AbstractQueuedSynchronizer {

        public Sync(int count) {
            setState(count);
        }

        //共享锁获取，返回值表示剩余资源的数量
        @Override
        protected int tryAcquireShared(int arg) {
            for (;;) {
                int currCount = getState();
                int newCount = currCount - arg;
                if ((newCount < 0) || compareAndSetState(currCount, newCount)) {
                    return newCount;
                }
            }
        }

        //共享锁的释放
        @Override
        protected boolean tryReleaseShared(int arg) {
            for (;;) {
                int currCount = getState();
                int newCount = currCount + arg;
                if (compareAndSetState(currCount, newCount)) {
                    return true;
                }
            }
        }

    }
}
