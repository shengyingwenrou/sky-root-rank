package cn.com.sourcetest.annotation;
import java.lang.annotation.*;

/**
 * desc: cn.com.sourcetest.annotation
 * User: sky.song@magicwifi.com.cn
 * Date: 2018/8/19
 * Time: 16:47
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AnnotationTest {
    String value() default "";
}
