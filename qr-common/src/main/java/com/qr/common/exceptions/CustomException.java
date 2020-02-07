package com.qr.common.exceptions;

/**
 * @author:Jascon
 * @date:2020-02-06 12:20
 */
public class CustomException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private int code;
    private String message;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }
    public CustomException(int code,String message) {
        super(message);
        this.code=code;
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
