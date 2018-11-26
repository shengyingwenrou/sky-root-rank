package cn.com.demo;

import cn.com.demo.dao.DemoDao;
import cn.com.demo.service.DemoService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by sky.song on 2018/11/20.
 */
public class ProxyFactory {

    private Object target;

    public ProxyFactory(Object target){
        this.target=target;
    }


    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("1");
                        Object object=method.invoke(target,args);
                        System.out.println("2");
                        return object;
                    }
                });
    }
}

class  TestFactory{

    public static void main(String[] args) {
        DemoService target = new DemoDao();
        DemoService proxy = (DemoService) new ProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass());
        proxy.save();
    }
}
