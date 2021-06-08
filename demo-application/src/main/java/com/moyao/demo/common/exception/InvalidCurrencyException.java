package com.moyao.demo.common.exception;

public class InvalidCurrencyException  extends BizException{

    public InvalidCurrencyException() {
        super(INVALID_CURRENCY_CODE, "金额单位非法");
    }
}
