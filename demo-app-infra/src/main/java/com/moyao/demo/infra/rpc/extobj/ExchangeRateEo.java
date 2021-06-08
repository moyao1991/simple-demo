package com.moyao.demo.infra.rpc.extobj;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ExchangeRateEo {

    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal rage;
}
