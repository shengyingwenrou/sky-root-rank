package cn.com.demo.impl;

import cn.com.demo.service.DemoService;

/**
 * Created by sky.song on 2018/11/20.
 */


public class DemoServiceImpl implements DemoService {

    @Override
    public void create() {
        System.out.println(" invoke  create  ... ");
    }

    @Override
    public void save() {
        System.out.println(" invoke  save  ... ");
    }

}
