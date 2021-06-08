package com.moyao.demo.common.exception;

public class AuthBizException extends BizException{

    public AuthBizException() {
        super(AUTH_CODE, "认证异常");
    }
}
