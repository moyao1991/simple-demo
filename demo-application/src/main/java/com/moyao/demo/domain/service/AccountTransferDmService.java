package com.moyao.demo.domain.service;

import com.moyao.demo.common.exception.DailyLimitExceededException;
import com.moyao.demo.domain.entity.Account;
import com.moyao.demo.domain.valueobject.ExchangeRate;
import com.moyao.demo.domain.valueobject.Money;

public interface AccountTransferDmService {

    void transfer(Account sourceAccount, Account targetAccount, Money targetMoney, ExchangeRate exchangeRate) throws DailyLimitExceededException;
}
