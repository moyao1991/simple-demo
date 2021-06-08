package com.moyao.demo.common.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.moyao.demo.domain.entity.Account;
import com.moyao.demo.domain.valueobject.AccountId;
import com.moyao.demo.domain.valueobject.AccountNumber;
import com.moyao.demo.domain.valueobject.Currency;
import com.moyao.demo.domain.valueobject.Money;
import com.moyao.demo.domain.valueobject.UserId;
import com.moyao.demo.infra.db.model.UsersDo;

@Mapper
public interface AccountConverter {

    AccountConverter CONVERTER = Mappers.getMapper(AccountConverter.class);

    default Account toAccount(UsersDo usersDo){
        Account account = new Account();
        account.setId(new AccountId(usersDo.getId()));
        account.setCurrency(new Currency(usersDo.getCurrency()));
        account.setAccountNumber(new AccountNumber(usersDo.getUsername()));
        account.setAvailable(new Money(usersDo.getAvailable(), account.getCurrency()));
        account.setDailyLimit(new Money(usersDo.getDailyLimit(), account.getCurrency()));
        account.setUserId(new UserId(usersDo.getId()));
        return account;
    }

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "username", source = "accountNumber.value")
    @Mapping(target = "currency", source = "currency.value")
    @Mapping(target = "available", source = "available.amout")
    @Mapping(target = "dailyLimit", source = "dailyLimit.amout")
    UsersDo toUsersDo(Account account);
}
