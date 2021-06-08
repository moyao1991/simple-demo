package com.moyao.demo.common.exception;

public class DailyLimitExceededException extends BizException {

    public DailyLimitExceededException() {
        super(DAILY_LIMIT_CODE, "转帐限额");
    }

}
