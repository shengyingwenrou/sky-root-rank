/*
 * Copyright (c) 2018 magicwifi.com.cn
 */
package cn.com.admin.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import cn.com.admin.user.entity.SysUser;
import cn.com.admin.user.entity.SysUserExample;
import cn.com.magicwifi.framework.core.mybatis.IBaseDao;
import cn.com.magicwifi.framework.core.mybatis.MybatisDao;

/**
 * SysUser
 *
 * User: Cosmo<87292008@qq.com> Date: 2018-11-21 Time: 16:09:20
 */
@MybatisDao
@Repository
public interface SysUserDao extends IBaseDao<SysUser, Long> {

    int countByExample(SysUserExample example);

    List<SysUser> selectListByExample(SysUserExample example);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int deleteByExample(SysUserExample example);
}