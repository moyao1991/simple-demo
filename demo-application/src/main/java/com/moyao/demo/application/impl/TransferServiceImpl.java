package com.moyao.demo.application.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.moyao.demo.application.TransferService;
import com.moyao.demo.common.cmd.TransferCommand;
import com.moyao.demo.common.converter.ExchangeRateConverter;
import com.moyao.demo.common.exception.DailyLimitExceededException;
import com.moyao.demo.domain.entity.Account;
import com.moyao.demo.domain.repository.AccountRepository;
import com.moyao.demo.domain.service.AccountTransferDmService;
import com.moyao.demo.domain.valueobject.AccountNumber;
import com.moyao.demo.domain.valueobject.Currency;
import com.moyao.demo.domain.valueobject.ExchangeRate;
import com.moyao.demo.domain.valueobject.Money;
import com.moyao.demo.domain.valueobject.UserId;
import com.moyao.demo.infra.rpc.extobj.ExchangeRateEo;
import com.moyao.demo.infra.rpc.extservice.ExchangeRateExService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TransferServiceImpl implements TransferService {

    //    private AuditMessageProducer auditMessageProducer;
    private final ExchangeRateExService exchangeRateExService;

    private final AccountTransferDmService accountTransferDmService;

    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public Boolean transfer(TransferCommand transferCommand) throws DailyLimitExceededException {
        Money targetMoney = new Money(transferCommand.getTargetAmount(), new Currency(transferCommand.getTargetCurrency()));

        Account sourceAccount = accountRepository.find(new UserId(transferCommand.getSourceUserId()));
        Account targetAccount = accountRepository.find(new AccountNumber(transferCommand.getTargetAccountNumber()));

        // 通过Converter将外部的转为domain valueobject
        ExchangeRateEo exchangeRateEo = exchangeRateExService.getExchangeRate(sourceAccount.getCurrency().getValue(), targetMoney.getCurrency().getValue());
        ExchangeRate exchangeRate = ExchangeRateConverter.CONVERTER.toExchangeRate(exchangeRateEo);

        accountTransferDmService.transfer(sourceAccount, targetAccount, targetMoney, exchangeRate);

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);
        //
        //        // 发送审计消息
        //        AuditMessage message = new AuditMessage(sourceAccount, targetAccount, targetMoney);
        //        auditMessageProducer.send(message);
        return true;
    }
}