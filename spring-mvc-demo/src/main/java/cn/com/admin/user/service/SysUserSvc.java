/*
 * Copyright (c) 2018 magicwifi.com.cn
 */
package cn.com.admin.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.admin.user.dao.SysUserDao;
import cn.com.admin.user.entity.SysUser;
import cn.com.magicwifi.framework.core.mybatis.BaseSvc;

/**
 * SysUser
 *
 * User: Cosmo<87292008@qq.com> Date: 2018-11-21 Time: 16:09:20
 */
@Transactional
@Service
public class SysUserSvc extends BaseSvc<SysUser, Long, SysUserDao> {

    @Autowired
    @Override
    public void setDao(SysUserDao sysUserDao) {
        super.setDao(sysUserDao);
    }

}