package cn.com.sourcetest.locks;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by sky.song on 2018/10/18.
 */
public class SpinLockTest implements Runnable {

    public synchronized void get() {
        System.out.println(Thread.currentThread().getId());
        set();
    }

    public synchronized void set() {
        System.out.println(Thread.currentThread().getId());
    }


    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        SpinLockTest ss = new SpinLockTest();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
    }
}


class Test implements Runnable {
    ReentrantLock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
    }


    public void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
    }


    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        Test ss = new Test();
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
    }

}

class SpinLock {

    //初始化为当前线程
    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock() {

        Lock a =new ReentrantLock();

        Thread current = Thread.currentThread();
        //null 不等于当前线程，返回false   !fasle=true进入自选
        while (!sign.compareAndSet(null, current)) {

        }

    }

    public void unlock() {

        Thread current = Thread.currentThread();
        //对比current= 初始化信息，所以为true，并设置为null，此时
//        while (!sign.compareAndSet(null, current))，所以，null=null，lock中自旋结束，
//      当一个锁完成，sign中有回到初始化状态。
        sign.compareAndSet(current, null);

    }


    public static void main(String[] args) {
        SpinLock spinLock =new SpinLock();
        spinLock.lock();


    }
}

