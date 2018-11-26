package com.cn.demo.dao;

import org.springframework.stereotype.Component;

/**
 * Created by sky.song on 2018/9/28.
 */

@Component
public class DemoDao {


    public void save() {

        System.out.println(" DemoDao save ");
    }

    public void update() {

        System.out.println(" DemoDao update ");
    }


}
