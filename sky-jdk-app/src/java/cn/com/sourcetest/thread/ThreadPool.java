package cn.com.sourcetest.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * desc: cn.com.SourceTest.thread
 * User: sky.song@magicwifi.com.cn
 * Date: 2018/8/8
 * Time: 17:05
 */
public class ThreadPool {


    public static void main(String[] args) {
        a();
    }


    public static void  blockingQueuePoolTest(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
        for (int i = 0; i < 15; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" + executor.getQueue().size() + "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }

    public static void a(){
        LinkedBlockingQueue<Runnable> queue =
                            new LinkedBlockingQueue<Runnable>(5);

        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
                ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, queue,handler);
                for (int i = 0; i < 16 ; i++)
                     {
                         threadPool.execute(
                                     new Thread(new ThreadPoolTest(), "Thread".concat(i + "")));
                        System.out.println("线程池中活跃的线程数： " + threadPool.getPoolSize());
                        if (queue.size() > 0)
                           {
                              System.out.println("----------------队列中阻塞的线程数" + queue.size());
                           }
                  }
                threadPool.shutdown();
    }
}


class MyTask implements Runnable {
    private int taskNum;
    public MyTask(int num) {
        this.taskNum = num;
    }
    @Override
    public void run() {
        System.out.println("正在执行task " + taskNum);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task " + taskNum + "执行完毕");
    }
}

class ThreadPoolTest implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}