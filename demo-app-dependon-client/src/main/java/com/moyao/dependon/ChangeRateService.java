package com.moyao.dependon;

public interface ChangeRateService {

    ChangeRate findChangeRateBy(String sourceCurrency, String targetCurrency);
}
