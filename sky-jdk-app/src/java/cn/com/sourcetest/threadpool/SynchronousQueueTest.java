package cn.com.sourcetest.threadpool;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by sky.song on 2018/10/11.
 */
public class SynchronousQueueTest {


    public static void main(String[] args) {

        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        // 定义一个Synchronous
        final SynchronousQueue<String> sq = new SynchronousQueue<String>();
// 定义一个数量为1的信号量，其作用相当于一个互斥锁
        final Semaphore sem = new Semaphore(1);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        sem.acquire();
                        String input = sq.take();
                        String output = TestDo.doSome(input);//内部类
                        System.out.println(Thread.currentThread().getName()+ ":" + output);
                        sem.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            String input = i + ""; //此处将i变成字符串
            try {
                sq.put(input);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }//End main

}

class TestDo {
    public static String doSome(String input) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String output = input + ":" + (System.currentTimeMillis() / 1000);
        return output;
    }

}
