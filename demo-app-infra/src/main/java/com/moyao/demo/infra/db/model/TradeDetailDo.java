package com.moyao.demo.infra.db.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TradeDetailDo {

    private Long id;

    private Long shopId;

    private String tid;

    private LocalDateTime created;

    private LocalDateTime modified;
}
