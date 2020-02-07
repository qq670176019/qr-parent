package com.qr.common.exceptions;

/**
 * 认证相关exception
 * @author:Jascon
 * @date:2020-02-06 12:17
 */
public class CustomAuthenticationException extends CustomException {
    public final static String IncorrectPassword="密码不正确";
    public final static String UnknownUsername="无效用户名";
    public final static String UserLocked="用户被锁定";
    public final static String UserDisabled="用户被禁用";

    public CustomAuthenticationException(int code, String message) {
        super(code, message);
    }
}
