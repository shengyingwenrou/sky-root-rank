package com.cn.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by sky.song on 2018/9/28.
 */
public class Demo {

    public Demo(int id, String name) {
        this.id = id;
        this.name = name;
        this.createTime = new Date();
    }

    private int id;

    private String name;

    @JSONField(format = "yyyy-MM-dd")
    private Date  createTime;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
