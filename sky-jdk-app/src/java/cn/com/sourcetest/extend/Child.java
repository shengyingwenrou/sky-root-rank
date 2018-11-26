package cn.com.sourcetest.extend;

/**
 * desc: cn.com.SourceTest.extend
 * User: sky.song@magicwifi.com.cn
 * Date: 2018/8/13
 * Time: 11:18
 */
public class Child extends Parent{


    public static String testStr="i am Child testStr";

    public static void main(String[] args) {
        Parent parent=new Child();
        System.out.println(parent.testStr);
        parent.testStr="111111111111111";
        System.out.println(parent.testStr);
    }

    public static void test(){
        testStr="aaaaaaaaaaaaaaaa";
    }


    public static void testFunction(){
        testStr="aaaaaaaaaaaaaaaa1";
    }

}
