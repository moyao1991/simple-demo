package com.moyao.demo.domain.valueobject;

public class Currency {

    private String value;

    public Currency(String value) {
        if (value == null || "".equals(value)) {
            throw new IllegalArgumentException("货币不能为空");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Currency) {
            return this.value.equals(((Currency) obj).value);
        }
        return super.equals(obj);
    }
}
