package com.cn.aop;

import java.lang.annotation.*;

/**
 * Created by sky.song on 2018/9/29.
 */


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodCall {

    String remark() default "";

    boolean checkToken() default  true;

}
