package cn.com.demo;

import cn.com.demo.dao.DemoDao;
import cn.com.demo.service.DemoService;

/**
 * Created by sky.song on 2018/11/20.
 */
public class ProxyFactory1 {

    private DemoService target;

    public ProxyFactory1(DemoDao target){
        this.target=target;
    }

    public void save(){
        target.save();
    }

}

class  Test{

    public static void main(String[] args) {
        ProxyFactory1  userProxy = new ProxyFactory1(new DemoDao());
        userProxy.save();
    }
}
