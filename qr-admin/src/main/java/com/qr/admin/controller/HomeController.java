package com.qr.admin.controller;


import com.qr.admin.mapper.AdminMapper;
import com.qr.admin.mapper.PermissionsMapper;
import com.qr.admin.mapper.RoleMapper;
import com.qr.admin.service.HomeService;
import com.qr.common.bean.ResultBean;
import com.qr.common.entity.AdminEntity;
import com.qr.common.entity.LoginInfoEntity;
import com.qr.common.entity.RoleEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Jascon
 * @Description:
 * @Date: 9:53 2020-01-21
 */
@Slf4j
@Controller
@RequestMapping(value = "/")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @RequestMapping(value = "index",method = RequestMethod.GET)
    @RequiresRoles("sys_manager")
    @RequiresPermissions("sys")
    public String index() {
        log.info("模板的index请求到了");
        return "pages/index";
    }

    //控制台
    @RequestMapping(value = "console",method = RequestMethod.GET)
    @RequiresPermissions("console")
    public String console() {
        log.info("模板的console请求到了");
        return "pages/console";
    }

    @RequestMapping(value = "404",method = RequestMethod.GET)
    public String notFound() {
        return "404";
    }

    @RequestMapping(value = "error2",method = RequestMethod.GET)
    public String error() {
        return "error2";
    }

    @RequestMapping(value = "noAuthor",method = RequestMethod.GET)
    public String noAuthor() {
        return "noAuthor";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login() {
        log.info("模板的login请求到了");
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResultBean<LoginInfoEntity> login(AdminEntity adminEntity) throws Exception {
        log.info("控制器的doLogin请求到了");
        return homeService.doLogin(adminEntity);
    }
}
