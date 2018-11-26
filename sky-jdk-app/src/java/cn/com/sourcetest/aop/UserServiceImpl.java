package cn.com.sourcetest.aop;

/**
 * Created by sky.song on 2018/9/21.
 */
public class UserServiceImpl implements UserService {


    @Override
    public void addUser(int id, String name) {
        System.out.println("++++UserServiceImpl.addUser=id:"+id+"_name:"+name);
    }
}
