package com.moyao.dependon;

import java.math.BigDecimal;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class ChangeRateServiceImpl implements ChangeRateService {
    @Override
    public ChangeRate findChangeRateBy(String sourceCurrency, String targetCurrency) {
        return ChangeRate.builder()
                .rage(BigDecimal.ONE)
                .sourceCurrency(sourceCurrency)
                .targetCurrency(targetCurrency)
                .build();
    }
}
