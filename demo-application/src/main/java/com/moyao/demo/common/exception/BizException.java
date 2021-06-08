package com.moyao.demo.common.exception;

public class BizException extends RuntimeException implements BizErrorCode{

    private String code;

    private String msg;

    public BizException(String code, String msg) {
        this(code, msg, null);
    }

    public BizException(String msg) {
       this(null, msg);
    }

    public BizException(String msg, Throwable e) {
       this(null, msg, e);
    }

    public BizException(String code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
