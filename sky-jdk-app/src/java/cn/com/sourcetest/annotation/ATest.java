package cn.com.sourcetest.annotation;

/**
 * Created by sky.song on 2018/11/13.
 */
public class ATest {


    public static void main(String[] args) {

            Hint hint = PersonTest.class.getAnnotation(Hint.class);
            System.out.println(hint);                   // null
            Hints hints1 = PersonTest.class.getAnnotation(Hints.class);
            System.out.println(hints1.value().length);  // 2
            Hint[] hints2 = PersonTest.class.getAnnotationsByType(Hint.class);
            System.out.println(hints2.length);

    }
}
