package cn.com.sourcetest.singleton;

/**
 * Created by sky.song on 2018/10/10.
 */
public class Singleton {

    public Singleton() {
    }


    // 饿汉式（静态常量）[可用]
    /**
    private final static Singleton INSTANCE = new Singleton();
    public static Singleton getInstance() {
        return INSTANCE;
    }
    **/

    // 饿汉式（静态代码块）[可用]
    /**
     private static Singleton INSTANCE;
     static{
     INSTANCE=new Singleton();
     }
     public Singleton getInstance(){
     return INSTANCE;
     }
     **/


    // 懒汉式(线程不安全)[不可用]
    /**
     private static Singleton  singleton;
     public Singleton getInstance(){
     if(null != singleton){
     singleton = new  Singleton();
     }
     return singleton;
     }
     **/

    // 懒汉式(线程安全，同步方法)[不推荐用]
   /**
    private static Singleton singleton;
    public static synchronized Singleton getInstance() {
        if (null != singleton) {
            singleton = new Singleton();
        }
        return singleton;
    }
    **/

    // 懒汉式(线程安全，同步代码块)[不可用]
    /**
    private static Singleton singleton;
    public static Singleton getInstance() {
        if (null != singleton) {
            synchronized (Singleton.class) {
                singleton = new Singleton();
            }
        }
        return singleton;
    }
    **/

    // 双重检查[推荐用]
    /**
    private static Singleton singleton;
    public static Singleton getInstance() {
        if (null != singleton) {
            synchronized (Singleton.class) {
                if (null != singleton) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
    **/

    // 静态内部类[推荐用]
//    private static class  SingletonInstance{
//        private static final Singleton INSTANCE = new Singleton();
//    }
//
//    public static Singleton getInstance(){
//        return SingletonInstance.INSTANCE;
//    }






}




