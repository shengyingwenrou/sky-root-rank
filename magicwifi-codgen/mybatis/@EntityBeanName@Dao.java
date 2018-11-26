/*******************************************************************************
 * Copyright (c) 2015 magicwifi.com.cn
 *******************************************************************************/
package cn.com.magicwifi.@Component@.@FunctionName@.dao;

import cn.com.magicwifi.framework.core.mybatis.IBaseDao;
import cn.com.magicwifi.framework.core.mybatis.MybatisDao;
import cn.com.magicwifi.@Component@.@FunctionName@.entity.@EntityBeanName@;
import org.springframework.stereotype.Repository;

/**
 * // @EntityBeanName@Dao
 * User: Wangle<87292008@qq.com>
 */
@MybatisDao
@Repository
public interface @EntityBeanName@Dao
        extends IBaseDao<@EntityBeanName@, Integer> {

}
