package cn.com.sourcetest.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * desc: cn.com.SourceTest.thread
 * User: sky.song@magicwifi.com.cn
 * Date: 2018/8/8
 * Time: 9:37
 */
public class ThreadRunnable {

    private static Logger logger = LoggerFactory.getLogger(ThreadRunnable.class);



    public static class Mytask implements Runnable{
        @Override
        public void run() {
            System.out.println("Thread is " + Thread.currentThread().getId()+"_"+Thread.currentThread().getName());
        }
    }



    public static void main(String[] args) {

       // ExecutorService executorService =Executors.newCachedThreadPool();

        ExecutorService executorService=new ThreadPoolExecutor(
                5,5,0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>()){
            


        };

        for(int i=0;i<20;i++){
            Mytask task=new Mytask();
           // task.
            executorService.submit(task);
        }

    }
}
