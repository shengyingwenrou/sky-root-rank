package com.cn.demo.service;

import com.cn.demo.entity.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sky.song on 2018/9/28.
 */

@Service
public class DemoService {

   // @Autowired
   // private DemoDao demoDao;

    @Autowired
    private DemoMapper demoMapper;

    public void likeName(String name) {
        demoMapper.likeName(name);
    }

    public void save(Demo demo) {
        demoMapper.save(demo);
    }

}
