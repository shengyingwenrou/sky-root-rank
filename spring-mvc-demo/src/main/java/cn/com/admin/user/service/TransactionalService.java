package cn.com.admin.user.service;

import cn.com.admin.user.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;

/**
 * Created by sky.song on 2018/11/25.
 */

public class TransactionalService {

    @Autowired
    private SysUserSvc sysUserSvc;

    private static SysUser sysUser;
    static {
        Date d= new Date();
        sysUser = new SysUser();
        sysUser.setCreateTime(d);
        sysUser.setLastLoginTime(d);
        sysUser.setName("skt test");
        sysUser.setPassword("12345678");
        sysUser.setUsername("sky name");
        sysUser.setRank(0);
        sysUser.setLoginCount(111);
        sysUser.setDisabled(true);
    }

    public void  add(){
        sysUser.setLastLoginIp("192.168.1.2");
        sysUserSvc.create(sysUser);

        add1();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW ,rollbackFor = Exception.class)
    public void  add1() throws RuntimeException{

        int a= 3/0;

        sysUser.setLastLoginIp("192.168.1.3");
        sysUserSvc.create(sysUser);
    }

}
