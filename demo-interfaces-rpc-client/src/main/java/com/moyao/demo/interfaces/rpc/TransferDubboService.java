package com.moyao.demo.interfaces.rpc;

import java.math.BigDecimal;

import com.moyao.demo.interfaces.rpc.common.Result;

public interface TransferDubboService {

    /**
     * 交易
     * @param sourceUserId 当前用户id
     * @param targetAccountNumber 目标帐号
     * @param targetAmount    金额
     * @param targetCurrency  币种
     * @return 交易结果
     */
    Result transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency) ;
}
