package com.qr.admin.controller;

import com.qr.admin.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:Jascon
 * @date:2020-02-05 11:56
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("rt/{id}")
    @ResponseBody
    public String rt(@PathVariable String id){
        redisUtils.set(id,id);
        boolean b=redisUtils.exists("k1");
        System.out.println("boolean: "+b);
        if(redisUtils.exists("k4")){
            String k4=redisUtils.get("k4").toString();
            System.out.println(k4);
        }

        return "完成";
    }
}
