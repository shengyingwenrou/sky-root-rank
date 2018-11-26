package com.cn.config;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sky.song on 2018/9/29.
 */


@ControllerAdvice
public class GlobalDefaultExceptionHandle {


    @ExceptionHandler
    @ResponseBody
    public String DefaultExceptionHandle(HttpServletRequest request,Exception es){

          return " sorry ";
    }
}
