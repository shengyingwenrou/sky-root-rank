package cn.com.sourcetest.annotation;

import java.lang.reflect.Field;

/**
 * desc: cn.com.sourcetest.annotation
 * User: sky.song@magicwifi.com.cn
 * Date: 2018/8/19
 * Time: 16:54
 */
public class AnnotationUtil {


    public static void getFruitInfo(Class<?> clazz){

        String strFrultName ="水果名称:";


        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(AnnotationTest.class)) {
                AnnotationTest fruitName = (AnnotationTest) field.getAnnotation(AnnotationTest.class);
                strFrultName = strFrultName + fruitName.value();
                System.out.println(strFrultName);
            }
        }

    }

    public static void main(String[] args) {
        getFruitInfo(AppleTest.class);
    }

}
