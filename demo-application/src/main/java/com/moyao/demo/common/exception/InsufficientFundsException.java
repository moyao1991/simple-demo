package com.moyao.demo.common.exception;

public class InsufficientFundsException extends BizException{

    public InsufficientFundsException() {
        super(INSUFFICIENT_FUNDS_CODE, "余额不足");
    }
}
