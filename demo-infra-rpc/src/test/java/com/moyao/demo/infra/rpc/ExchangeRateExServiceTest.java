package com.moyao.demo.infra.rpc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.moyao.demo.infra.rpc.extobj.ExchangeRateEo;
import com.moyao.demo.infra.rpc.extservice.ExchangeRateExService;
import lombok.extern.slf4j.Slf4j;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = RpcApplication.class)
@Slf4j
public class ExchangeRateExServiceTest {

    @Autowired
    private ExchangeRateExService exchangeRateExService;

    @Test
    public void assertExchangeRate(){
        ExchangeRateEo exchangeRateEo = exchangeRateExService.getExchangeRate("y", "y");
        Assert.assertTrue(exchangeRateEo != null);
        log.info("rage:{}", exchangeRateEo.getRage());
    }
}
