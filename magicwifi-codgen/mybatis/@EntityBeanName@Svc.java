/*******************************************************************************
 * Copyright (c) 2015 magicwifi.com.cn
 *******************************************************************************/
package cn.com.magicwifi.@Component@.@FunctionName@.service;

import cn.com.magicwifi.framework.core.mybatis.BaseSvc;
import cn.com.magicwifi.@Component@.@FunctionName@.dao.@EntityBeanName@Dao;
import cn.com.magicwifi.@Component@.@FunctionName@.entity.@EntityBeanName@;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * // @EntityBeanName@Svc
 * User: Wangle<87292008@qq.com>
 */
@Transactional
@Service
public class @EntityBeanName@Svc
        extends BaseSvc<@EntityBeanName@, Integer, @EntityBeanName@Dao> {

    @Autowired
    @Override
    public void setDao(@EntityBeanName@Dao dao) {
        super.setDao(dao);
    }
}
