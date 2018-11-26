package cn.com.sourcetest.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc: cn.com.SourceTest.rabbitmq
 * User: sky.song@magicwifi.com.cn
 * Date: 2018/8/3
 * Time: 16:10
 */
public class NewThread extends  Thread{


    private static Logger logger = LoggerFactory.getLogger(NewThread.class);

    public static volatile int volatilePara=2;
    public static  int volatileIntPara=2;

    @Override
    public void run (){

        logger.error("这是一个现场 {} {}","test","test");
    }

    public static void main(String[] args) {

//        String a="11";
//        NewThread n1=new NewThread();
//        NewThread n2=new NewThread();
//        n1.start();
//        n2.start();

         int version=1;


        Thread t1= new Thread(new ThreadTest(version));
        t1.start();

        version++;
        Thread t2= new Thread(new ThreadTest(version));
        t2.start();
    }
}


class ThreadTest implements Runnable {
    private int version;

    public ThreadTest(int version) {
        this.version = version;
    }

    @Override
    public void run() {

        System.out.println("AAAAAAAAAAAAAAA" + version);
    }
}

class Test {
    public volatile int inc = 0;

    public void increase() {
        synchronized (Test.class){
            inc++;
            System.out.println(Thread.currentThread().getName()+":inc:"+inc);
        }

    }

    public static void main(String[] args) {
        final Test test = new Test();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<100;j++)

                        test.increase();
                };
            }.start();
        }

        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }
}




