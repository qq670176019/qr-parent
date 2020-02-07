package com.qr.common.controller;

import com.qr.common.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:Jascon
 * @date:2020-01-17 10:26
 */
@RestController
@RequestMapping("/redis")
public class RedisControllerTest {
    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("rt/{id}")
    public String rt(){
        redisUtils.set("k6","k6");
        boolean b=redisUtils.exists("k1");
        String k4=redisUtils.get("k4").toString();
        return k4+",test";
    }
}
