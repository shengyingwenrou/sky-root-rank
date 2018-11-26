package cn.com.sourcetest.overloadingTwo;

import cn.com.sourcetest.overloading.OverloadingTest;

/**
 * desc: cn.com.sourcetest.overloadingTwo
 * User: sky.song@magicwifi.com.cn
 * Date: 2018/8/19
 * Time: 15:21
 */
public class Father extends OverloadingTest{


    @Override
    public void work(){

    }

    public static void main(String[] args) {
        Father father=new Father();
        father.work();

    }

}
