package cn.com.sourcetest.threadpool;

import cn.com.sourcetest.thread.ThreadRunnable;

import java.util.concurrent.*;

/**
 * Created by sky.song on 2018/10/11.
 */
public class ThreadPoolTest {

    private static int corePoolSize=2;
    private static int maximumPoolSize=3;
    private static long keepAliveTime=0L;
    private static TimeUnit unit=TimeUnit.MILLISECONDS;

   // private static BlockingQueue<> threadQueue= new PriorityBlockingQueue<>(5);
    static {

    }

   //private static LinkedBlockingQueue<PriorityTask> queue = new LinkedBlockingQueue<>(1);

    private static LinkedBlockingQueue<PriorityTask> queue = new LinkedBlockingQueue<>(1);

    private static ThreadPoolExecutor executors = null;



    public static void policy(RejectedExecutionHandler handler) {
        if (null == handler) {
            handler = new MyRejectedHandle();
        }
        executors = new PriorityThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, new PriorityBlockingQueue<>(5), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("sky_thread_" + thread.getId());
                return thread;
            }
        }, handler) {

        };

        for (int i = 0; i < 10; i++) {
            executors.submit(new PriorityTask(executors,String.valueOf(i),1));
        }
        executors.shutdown();
    }


    public static void main(String[] args) {
        //policy(null);


       policy((new ThreadPoolExecutor.AbortPolicy()));
        // taskProvide();
        // System.out.println("threadQueue left size:"+threadQueue.size());
    }

    public static void taskProvide(){
        //System.out.println("threadQueue left size:"+threadQueue.size());
        //System.out.println("queue left size:"+queue.size());
    }

}

class PriorityTask  implements Runnable{

    private ThreadPoolExecutor es;
    private String name;
    private int priority;
    private int id;
    private static int count = 0;

    public PriorityTask(ThreadPoolExecutor es, String name, int priority) {
        this.id=++count;
        this.es = es;
        this.name = name;
        this.priority = priority;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(3);//休眠3秒
            //System.out.println(Thread.currentThread().getName() + " invoke ");
            //return this.priority;
        }catch (InterruptedException  e){
            System.out.println(Thread.currentThread().getName()+" is exception ! ");
        }
        System.out.println("任务:"+id  +"__"+ Thread.currentThread().getName() + " invoke over ");
           //return 0;
    }
}


class MyRejectedHandle implements RejectedExecutionHandler{
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println(" Runnable Rejected:" + Thread.currentThread().getName());
    }
}


