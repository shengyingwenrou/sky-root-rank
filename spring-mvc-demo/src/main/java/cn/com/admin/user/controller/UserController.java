package cn.com.admin.user.controller;

import cn.com.admin.user.entity.SysUser;
import cn.com.admin.user.service.SysUserSvc;
import cn.com.common.DataSource;
import cn.com.magicwifi.framework.core.datasource.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by sky.song on 2018/9/27.
 */


@Controller
@RequestMapping(value = "demo")
public class UserController {


   @Autowired
   private SysUserSvc sysUserSvc;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(HttpServletResponse response, Model model){



        DBContextHolder.setDBKey(DataSource.DEFAULT);
        List<SysUser>  userList = sysUserSvc.getAll();
        model.addAttribute("userList",userList);
        model.addAttribute("fremmarker","fremmarker ");
        return "demo/index";
    }
}
