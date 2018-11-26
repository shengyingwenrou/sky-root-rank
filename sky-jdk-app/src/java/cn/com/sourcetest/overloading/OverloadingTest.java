package cn.com.sourcetest.overloading;

/**
 * desc: cn.com.sourcetest.overloading
 * User: sky.song@magicwifi.com.cn
 * Date: 2018/8/17
 * Time: 11:04
 */
public class OverloadingTest {

    /**
    重载(overloading) 是在一个类里面，方法名字相同，而参数不同。返回类型可以相同也可以不同。
    每个重载的方法（或者构造函数）都必须有一个独一无二的参数类型列表。
    最常用的地方就是构造器的重载。

    重载规则:
    被重载的方法必须改变参数列表(参数个数或类型不一样)；
    被重载的方法可以改变返回类型；
    被重载的方法可以改变访问修饰符；
    被重载的方法可以声明新的或更广的检查异常；
    方法能够在同一个类中或者在一个子类中被重载。
    无法以返回值类型作为重载函数的区分标准。
    **/

    protected   void work(){
           System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }


    public static void main(String[] args) {

        OverloadingTest overloadingTest=new OverloadingTest();
        overloadingTest.work();
    }


    @Override
    public  void finalize(){

    }


}
