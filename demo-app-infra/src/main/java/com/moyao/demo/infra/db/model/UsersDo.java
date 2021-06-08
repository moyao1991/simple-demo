package com.moyao.demo.infra.db.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UsersDo {

    private Long id;

    private String username;

    private String currency;

    private BigDecimal available;

    private BigDecimal dailyLimit;

    private LocalDateTime created;

    private LocalDateTime modified;

}
