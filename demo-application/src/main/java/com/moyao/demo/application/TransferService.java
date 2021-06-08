package com.moyao.demo.application;

import com.moyao.demo.common.cmd.TransferCommand;
import com.moyao.demo.common.exception.DailyLimitExceededException;

/**
 *  交易服务
 */
public interface TransferService {

    /**
     * 转帐服务
     * @param transferCommand 转帐信息
     * @return 是否成功
     * @throws DailyLimitExceededException 每日转帐限制异常
     */
    Boolean transfer(TransferCommand transferCommand) throws DailyLimitExceededException;

}