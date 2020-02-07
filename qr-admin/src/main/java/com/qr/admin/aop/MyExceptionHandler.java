package com.qr.admin.aop;

import com.qr.common.bean.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:Jascon
 * @date:2020-02-03 16:42
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {
    @ExceptionHandler
    public String ErrorHandler(AuthorizationException e) {
        log.error("没有通过权限验证！", e);
        return "/noAuthor";
    }

    @ExceptionHandler
    @ResponseBody
    public ResultBean AuthenticationExceptionHandler(AuthenticationException e){
        log.error("用户名或密码错误",e);
        ResultBean result=new ResultBean();
        result.setMsg("用户名或密码错误");
        result.setCode(1);
        return result;
    }
}
