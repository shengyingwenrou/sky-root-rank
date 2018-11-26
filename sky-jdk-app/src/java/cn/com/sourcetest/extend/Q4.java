package cn.com.sourcetest.extend;

/**
 * Created by sky.song on 2018/10/30.
 */
class Super {
    //String s;
    public Super() {
        System.out.println("Super");
    }
}

class Sub extends Super {
    public Sub() {
        System.out.println("Sub");
    }

}

public class Q4 {
    public static void main(String[] args) {
        Sub s = new Sub();
    }
}