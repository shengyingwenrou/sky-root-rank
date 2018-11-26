package cn.com.sourcetest.locks;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by sky.song on 2018/10/18.
 */
public class AtomicReferenceTest {


    private AtomicReference<Thread> owner =new AtomicReference<>();
    private int count =0;
    public void lock(){
        Thread current = Thread.currentThread();
        if(current==owner.get()) {
            count++;
            return ;
        }
        while(!owner.compareAndSet(null, current)){
        }
    }
    public void unlock (){
        Thread current = Thread.currentThread();
        if(current==owner.get()){
            if(count!=0){
                count--;
            }else{
                owner.compareAndSet(current, null);
            }
        }
    }

    public static void main(String[] args) {
        AtomicReferenceTest test=new AtomicReferenceTest();
        test.lock();
    }

}

