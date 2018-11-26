package cn.com.sourcetest.Interfacetest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by sky.song on 2018/9/21.
 */
public class InvokeHandler implements InvocationHandler {

    private Object targetObject;


    public Object createInstanceObject(Object object){
        this.targetObject=object;

        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        check();
        Object object = method.invoke(targetObject,args);
        return object;
    }


    public void check(){
        System.out.println("++++InvokeHandler.check ");
    }

}
