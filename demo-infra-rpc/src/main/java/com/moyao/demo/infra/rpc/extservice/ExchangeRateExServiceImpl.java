package com.moyao.demo.infra.rpc.extservice;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import com.moyao.demo.infra.rpc.converter.ChangeRateConverter;
import com.moyao.demo.infra.rpc.extobj.ExchangeRateEo;
import com.moyao.dependon.ChangeRate;
import com.moyao.dependon.ChangeRateService;

@Service
public class ExchangeRateExServiceImpl implements ExchangeRateExService {

    @DubboReference(check = false)
    private ChangeRateService changeRateService;

    @Override
    public ExchangeRateEo getExchangeRate(String sourceCurrency, String targetCurrency) {
        ChangeRate changeRate = changeRateService.findChangeRateBy(sourceCurrency,targetCurrency);
        return ChangeRateConverter.CONVERTER.toExchangeRateEo(changeRate);
    }
}
