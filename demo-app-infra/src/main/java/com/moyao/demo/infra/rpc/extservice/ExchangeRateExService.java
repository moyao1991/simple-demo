package com.moyao.demo.infra.rpc.extservice;

import com.moyao.demo.infra.rpc.extobj.ExchangeRateEo;

public interface ExchangeRateExService {

    ExchangeRateEo getExchangeRate(String sourceCurrency, String targetCurrency);
}
