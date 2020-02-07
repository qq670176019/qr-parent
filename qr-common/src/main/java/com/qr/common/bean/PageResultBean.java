package com.qr.common.bean;

import java.io.Serializable;

/**
 * @author:Jascon
 * @date:2020-01-16 15:52
 *
 */
public class PageResultBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int NO_LOGIN = -1;

    public static final int SUCCESS = 0;

    public static final int CHECK_FAIL = 1;

    public static final int NO_PERMISSION = 2;

    public static final int UNKNOWN_EXCEPTION = -99;


    /**
     * 返回的信息(主要出错的时候使用)
     */
    private String msg = "success";

    private int count;
    /**
     * 接口返回码, 0表示成功, 其他看对应的定义
     * 晓风轻推荐的做法是:
     * 0   : 成功
     * >0 : 表示已知的异常(例如提示错误等, 需要调用地方单独处理)
     * <0 : 表示未知的异常(不需要单独处理, 调用方统一处理)
     */
    private int code = SUCCESS;

    /**
     * 返回的数据
     */
    private T data;

    public PageResultBean() {
        super();
    }

    public PageResultBean(T data) {
        super();
        this.data = data;
    }
    public PageResultBean(T data,int count) {
        super();
        this.data = data;
        this.count=count;
    }

    public PageResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = UNKNOWN_EXCEPTION;
    }

    @Override
    public String toString() {
        return "PageResultBean{" +
                "msg='" + msg + '\'' +
                ", count=" + count +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
