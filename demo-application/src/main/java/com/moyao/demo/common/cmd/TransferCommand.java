package com.moyao.demo.common.cmd;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
public class TransferCommand {

    private Long sourceUserId;

    private String targetAccountNumber;

    private BigDecimal targetAmount;

    private String targetCurrency;
}
