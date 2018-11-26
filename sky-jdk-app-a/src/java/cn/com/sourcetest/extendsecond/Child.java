package cn.com.sourcetest.extendsecond;

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


    @Override
    public  void testFunctionF(Child child){
        testStr="aaaaaaaaaaaaaaaa1";
    }

    public  void testFunctionF(Child child,String i){
        testStr="aaaaaaaaaaaaaaaa1";
    }

    public  boolean testFunctionF(Child child,String i,String r){
        testStr="aaaaaaaaaaaaaaaa1";
        return false;
    }

}
