package cn.com.sourcetest.statictest;

/**
 * Created by sky.song on 2018/10/13.
 */



 class A {

    static {
        System.out.println("1");
    }

    public A() {
        System.out.println("2");
    }
}

 class B extends A{

    static {
        System.out.println("a");
    }

    public B() {
        System.out.println("b");
    }
}

public class Hello{

    public static void main(String[] args) {

        //A ab = new B();
        // ab = new B();

        try {
            throw new ExampleB("B");
        } catch (ExampleA e) {
            System.out.println("ExampleA");
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
}


class ExampleA extends Exception {

    public ExampleA() {
        //System.out.println("ExampleA:");
    }
}

class ExampleB extends ExampleA {

    public ExampleB(String a) {
        //System.out.println("ExampleB:"+a);
    }
}



