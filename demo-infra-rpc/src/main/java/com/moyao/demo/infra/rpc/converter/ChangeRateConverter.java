package com.moyao.demo.infra.rpc.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.moyao.demo.infra.rpc.extobj.ExchangeRateEo;
import com.moyao.dependon.ChangeRate;

@Mapper
public interface ChangeRateConverter {

    ChangeRateConverter CONVERTER = Mappers.getMapper(ChangeRateConverter.class);

    ExchangeRateEo toExchangeRateEo(ChangeRate exchangeRate);
}
