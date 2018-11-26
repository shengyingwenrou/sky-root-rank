package com.cn.demo.service;

import com.cn.demo.entity.Demo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by sky.song on 2018/9/28.
 */
public interface DemoMapper {

    //#{name}:参数占位符
    @Select("select *from demo where name=#{name}")
    public List<Demo> likeName(String name);


    @Select("select *from demo where id = #{id}")
    public Demo getById(long id);

    @Select("select name from demo where id = #{id}")
    public String getNameById(long id);

    @Insert("insert into demo(name,create_time) values(#{name},#{createTime})")
    @Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
    public void save(Demo demo);
}
