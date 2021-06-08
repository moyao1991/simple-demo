package com.moyao.demo.domain.entity;

import com.moyao.demo.common.exception.DailyLimitExceededException;
import com.moyao.demo.common.exception.InsufficientFundsException;
import com.moyao.demo.common.exception.InvalidCurrencyException;
import com.moyao.demo.domain.valueobject.AccountId;
import com.moyao.demo.domain.valueobject.AccountNumber;
import com.moyao.demo.domain.valueobject.Currency;
import com.moyao.demo.domain.valueobject.Money;
import com.moyao.demo.domain.valueobject.UserId;

import lombok.Data;

/**
 *  用户帐号实体
 */

@Data
public class Account {

    private AccountId id;
    private AccountNumber accountNumber;
    private UserId userId;
    private Money available;
    private Money dailyLimit;
    private Currency currency;

    /**
     * 转出操作，因为此方法只改变Account的值，所以写在方法内
     * @param money 转出金额
     */
    public void withdraw(Money money) throws InvalidCurrencyException, DailyLimitExceededException {
        if (this.available.compareTo(money) < 0){
            throw new InsufficientFundsException();
        }

        if (this.dailyLimit.compareTo(money) < 0){
            throw new DailyLimitExceededException();
        }

        if (!this.getCurrency().equals(money.getCurrency())){
            throw new InvalidCurrencyException();
        }
        this.available = this.available.subtract(money);
    }

    // 转入
    public void deposit(Money money) throws InvalidCurrencyException {
       if (!this.getCurrency().equals(money.getCurrency())){
           throw new InvalidCurrencyException();
       }

       this.available = this.available.add(money);

    }
}
