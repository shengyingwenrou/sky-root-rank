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

    @Override
    public void run (){

        logger.error("这是一个现场 {} {}","test","test");
    }

    public static void main(String[] args) {
        NewThread n1=new NewThread();
        NewThread n2=new NewThread();
        n1.start();
        n2.start();
    }





}
