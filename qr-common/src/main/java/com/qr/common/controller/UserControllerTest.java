package com.qr.common.controller;

import com.qr.common.utils.RedisUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.qr.common.service.UserService;

@RestController
@RequestMapping("/user")
public class UserControllerTest {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("getUser/{id}")
    public String getUser(@PathVariable int id){
        return userService.set(id).toString();
    }

}
