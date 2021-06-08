package com.moyao.dependon;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChangeRate implements Serializable{

    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal rage;
}
