package cn.com.sourcetest.annotation;

import java.lang.annotation.*;

/**
 * Created by sky.song on 2018/11/13.
 */
@Target( ElementType.TYPE )
@Retention( RetentionPolicy.RUNTIME)
@interface Hints {
    Hint[] value();
}


@Target( ElementType.TYPE )
@Retention( RetentionPolicy.RUNTIME)
@Repeatable(Hints.class)
@interface Hint {
    String value();
}
