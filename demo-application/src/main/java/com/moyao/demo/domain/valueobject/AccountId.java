package com.moyao.demo.domain.valueobject;

public class AccountId {

    private Long value;

    public AccountId(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}



