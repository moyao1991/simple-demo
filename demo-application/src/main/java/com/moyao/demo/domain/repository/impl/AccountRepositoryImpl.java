package com.moyao.demo.domain.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moyao.demo.common.converter.AccountConverter;
import com.moyao.demo.domain.entity.Account;
import com.moyao.demo.domain.repository.AccountRepository;
import com.moyao.demo.domain.valueobject.AccountId;
import com.moyao.demo.domain.valueobject.AccountNumber;
import com.moyao.demo.domain.valueobject.Currency;
import com.moyao.demo.domain.valueobject.UserId;
import com.moyao.demo.infra.db.dao.UserDao;
import com.moyao.demo.infra.db.model.UsersDo;

/**
 *  domain account持久表对表
 */
@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private UserDao userDao;

    @Override
    public Account find(AccountNumber accountNumber) {
        UsersDo usersDo = userDao.selectByUserName(accountNumber.getValue());
        return AccountConverter.CONVERTER.toAccount(usersDo);
    }

    @Override
    public Account find(UserId userId) {
         UsersDo usersDo = userDao.selectById(userId.getId());
         return AccountConverter.CONVERTER.toAccount(usersDo);
    }

    @Override
    public Account save(Account account) {
         userDao.save(AccountConverter.CONVERTER.toUsersDo(account));
        return account;
    }
}
