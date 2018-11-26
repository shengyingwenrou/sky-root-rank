package com.cn.demo.controller;

import com.cn.aop.MethodCall;
import com.cn.demo.entity.Demo;
import com.cn.demo.service.DemoService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * Created by sky.song on 2018/9/28.
 */


@Controller
@RequestMapping(value = "demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @ResponseBody
    @RequestMapping(value = "index", method = RequestMethod.GET)
    @MethodCall(remark = "home page", checkToken = false)
    public String index() {
        //demoService.add();
        return "Hello World";
    }

    @ResponseBody
    @RequestMapping(value = "json", method = RequestMethod.GET)
    @MethodCall(remark = "json test", checkToken = false)
    public Demo json() {
        Demo demo = new Demo(1, "sky.song1");
        //demoService.update();
        return demo;
    }


    @RequestMapping(value = "ftl", method = RequestMethod.GET)
    @MethodCall(remark = "map test ", checkToken = false)
    public String ftl(Model model) {
        model.addAttribute("demo", new Demo(1,"sky.song"));
        Map map = Maps.newConcurrentMap();
        map.put("1",new Demo(2, "sky.song2"));
        map.put("2",new Demo(3, "sky.song3"));
        model.addAttribute("maps", map);
        return "demo/index";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    @MethodCall(remark = "Mybatis insert")
    public String create(Model model) {
       Demo demo = new Demo(0,"sky.song");
        demoService.save(demo);
        return "demo/index";
    }


    @RequestMapping(value = "ex", method = RequestMethod.GET)
    @MethodCall(remark = "ex")
    @ResponseBody
    public String ex(Model model) {
        Demo demo = new Demo(0,"sky.song");
        demoService.save(demo);
        return "ex";
    }

}
