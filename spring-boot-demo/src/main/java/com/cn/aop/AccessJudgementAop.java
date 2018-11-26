package com.cn.aop;

import com.google.common.collect.Maps;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by sky.song on 2018/9/29.
 */


@Aspect
@Component
public class AccessJudgementAop extends BaseAop {

    private Logger logger = LoggerFactory.getLogger(AccessJudgementAop.class);

    @Pointcut("@annotation(com.cn.aop.MethodCall)")
    public void methodCallPointCut(){}


    @Before("AccessJudgementAop.methodCallPointCut()")
    public void before(JoinPoint joinPoint){

        Method method =getPointcutMethod(joinPoint);
        System.out.println("AccessJudgementAop before: " +method.getName());

        HttpServletRequest request = getHttpRequest();

        Map<String, String> params = Maps.newHashMap();
        Enumeration<String> parameterNames = request.getParameterNames();

        //String parameterName;
        //String parameterValue;

        while (parameterNames.hasMoreElements()) {
            //parameterName = parameterNames.nextElement();
            //parameterValue = ServletUtils.getParameter(request, parameterName);
            //params.put(parameterName, parameterName);
        }

    }


    @Around("methodCallPointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object object = proceedingJoinPoint.proceed();
        Method method = getPointcutMethod(proceedingJoinPoint);
        System.out.println("AccessJudgementAop around: " + method.getName());
        return object;

    }

    @AfterThrowing(value = "methodCallPointCut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint,Exception ex){
        Method method = getPointcutMethod(joinPoint);
        System.out.println("AccessJudgementAop afterThrowing: " + method.getName());

        HttpServletRequest request = getHttpRequest();
        if (null == request) {

        }
    }


    protected Method getPointcutMethod(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod();
    }

    protected HttpServletRequest getHttpRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == requestAttributes) {
           // throw new WebApiException(ErrorCode.INTERNAL_SERVER_ERROR, "BaseComponent获取HttpServletRequest失败");
        }
        return requestAttributes.getRequest();
    }



}
