package com.moyao.demo.interfaces.rpc;

import java.math.BigDecimal;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.moyao.demo.application.TransferService;
import com.moyao.demo.common.cmd.TransferCommand;
import com.moyao.demo.interfaces.rpc.common.Result;

@DubboService
public class TransferDubboServiceImpl implements TransferDubboService {

    @Autowired
    private TransferService transferService;

    @Override
    public Result transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, String targetCurrency){
        TransferCommand command = new TransferCommand();
        command.setSourceUserId(sourceUserId);
        command.setTargetAccountNumber(targetAccountNumber);
        command.setTargetAmount(targetAmount);
        command.setTargetCurrency(targetCurrency);
        return transferService.transfer(command) ? Result.success() : Result.fail();
    }
}
