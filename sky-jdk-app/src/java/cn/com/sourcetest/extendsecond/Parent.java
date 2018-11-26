package cn.com.sourcetest.extendsecond;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * desc: cn.com.SourceTest.extend
 * User: sky.song@magicwifi.com.cn
 * Date: 2018/8/13
 * Time: 11:14
 */
public class Parent {


    private static Logger logger= LoggerFactory.getLogger(Parent.class);

    public  String testStr  = "i am Parent testStr ";


    public Parent() {
        testStr = "i am Parent Parent() testStr ";
    }

    public Parent(String testStr) {
        this.testStr = testStr;
    }

    public  void testFunctionF(Child child){
        logger.debug(" i am Parent testFunction ");
    }

}
