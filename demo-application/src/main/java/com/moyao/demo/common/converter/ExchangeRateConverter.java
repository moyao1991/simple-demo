package com.moyao.demo.common.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.moyao.demo.domain.valueobject.Currency;
import com.moyao.demo.domain.valueobject.ExchangeRate;
import com.moyao.demo.infra.rpc.extobj.ExchangeRateEo;

@Mapper
public interface ExchangeRateConverter {

    ExchangeRateConverter CONVERTER = Mappers.getMapper(ExchangeRateConverter.class);

    default ExchangeRate toExchangeRate(ExchangeRateEo exchangeRateEo){
        return new ExchangeRate(exchangeRateEo.getRage(),
                new Currency(exchangeRateEo.getSourceCurrency()),
                        new Currency(exchangeRateEo.getTargetCurrency()));
    }
}
