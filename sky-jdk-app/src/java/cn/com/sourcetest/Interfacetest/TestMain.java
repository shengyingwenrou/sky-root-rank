package cn.com.sourcetest.Interfacetest;

import cn.com.sourcetest.aop.UserService;
import cn.com.sourcetest.aop.UserServiceImpl;

/**
 * Created by sky.song on 2018/9/21.
 */
public class TestMain {


    public static void main(String[] args) {

        InvokeHandler invokeHandler = new InvokeHandler();

        UserService userService = (UserService)invokeHandler.createInstanceObject(new UserServiceImpl());
        userService.addUser(1,"sky.song");
    }
}
