package com.moyao.demo.domain.repository;

import com.moyao.demo.domain.entity.Account;
import com.moyao.demo.domain.valueobject.AccountNumber;
import com.moyao.demo.domain.valueobject.UserId;

public interface AccountRepository {
    Account find(AccountNumber accountNumber);
    Account find(UserId userId);
    Account save(Account account);
}
