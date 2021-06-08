package com.moyao.demo.domain.service.impl;

import org.springframework.stereotype.Service;

import com.moyao.demo.common.exception.DailyLimitExceededException;
import com.moyao.demo.domain.entity.Account;
import com.moyao.demo.domain.service.AccountTransferDmService;
import com.moyao.demo.domain.valueobject.ExchangeRate;
import com.moyao.demo.domain.valueobject.Money;

@Service
public class AccountTransferDmServiceImpl implements AccountTransferDmService {

    /**
     *  两帐户相互汇款，涉及两entity的变动，所以放在domainService处理
     *  domainService方法同entity方法一样，只算对象状态的变化，不写外界交互
     *  domainService只是对entity方法的补足
     */
    @Override
    public void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate) throws  DailyLimitExceededException {
        Money sourceMoney =  exchangeRate.exchageTo(targetMoney);
        sourceAccount.withdraw(sourceMoney);
        targetAccount.deposit(targetMoney);
    }
}
