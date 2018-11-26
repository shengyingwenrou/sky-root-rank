package cn.com.sourcetest.threadpool;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by sky.song on 2018/10/11.
 */
public class PriorityBlockQueueDemo {



    public static void main(String[] args) {
        PriorityBlockingQueue<User> queue = new PriorityBlockingQueue<User>();
        for (int i = 0; i < 12; i++) {
            User user = new User();
            int max = 20;
            int min = 10;
            Random random = new Random();

            int n = random.nextInt(max) % (max - min + 1) + min;

            user.setPriority(n);
            user.setUsername("李艳第" + i + "天");

            queue.add(user);
        }

        for (int i = 0; i < 12; i++) {
            User u = queue.poll();
            System.out.println("优先级是：" + u.getPriority() + "," + u.getUsername());
        }
    }



}

 class User implements Comparable<User> {
    private Integer priority;
    private String username;

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *      *
     *      * @Description:当前对象和其他对象做比较，当前优先级大就返回-1，优先级小就返回1
     *      * 值越小优先级越高
     *      * @param TODO
     *      * @author yaomingyang
     *      * @date 2017年8月27日 上午11:28:10
     *     
     */
    @Override
    public int compareTo(User user) {
        //System.out.println("比较结果"+this.priority.compareTo(user.getPriority()));
        return this.priority.compareTo(user.getPriority());
    }
}



