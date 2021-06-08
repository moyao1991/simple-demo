package com.moyao.demo.common.exception;

public class BizDataException extends BizException {

    private Object data;

    public BizDataException(String code, String msg, Object data) {
        super(code, msg);
        this.data = data;
    }

    public BizDataException(String msg, Object data) {
        super(msg);
        this.data = data;
    }

    public BizDataException(String msg, Throwable e, Object data) {
        super(msg, e);
        this.data = data;
    }

    public BizDataException(String code, String msg, Throwable e, Object data) {
        super(code, msg, e);
        this.data = data;
    }

    public <T> T getData() {
        return (T)data;
    }
}
