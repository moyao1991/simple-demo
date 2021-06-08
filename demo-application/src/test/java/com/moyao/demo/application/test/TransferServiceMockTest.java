package com.moyao.demo.application.test;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.moyao.demo.DemoApplication;
import com.moyao.demo.application.TransferService;
import com.moyao.demo.common.cmd.TransferCommand;
import com.moyao.demo.infra.db.dao.UserDao;
import com.moyao.demo.infra.db.model.UsersDo;
import com.moyao.demo.infra.rpc.extobj.ExchangeRateEo;
import com.moyao.demo.infra.rpc.extservice.ExchangeRateExService;

import lombok.extern.slf4j.Slf4j;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@Slf4j
public class TransferServiceMockTest {

    @MockBean
    protected UserDao userDao;

    @MockBean
    private ExchangeRateExService exchangeRateExService;

    @Autowired
    private TransferService transferService;


    @Test
    public void assertTransfer(){
        TransferCommand transferCommand = new TransferCommand();
        transferCommand.setSourceUserId(1L);
        transferCommand.setTargetAccountNumber("110");
        transferCommand.setTargetCurrency("y");
        transferCommand.setTargetAmount(new BigDecimal(1));

        initMockData();

        transferService.transfer(transferCommand);
    }

    private void initMockData(){
        ExchangeRateEo exchangeRateEo = new ExchangeRateEo();
        exchangeRateEo.setSourceCurrency("y");
        exchangeRateEo.setTargetCurrency("y");
        exchangeRateEo.setRage(new BigDecimal(1));
        when(exchangeRateExService.getExchangeRate(anyString(),anyString())).thenReturn(exchangeRateEo);


        UsersDo usersDo1 = new UsersDo();
        usersDo1.setId(1L);
        usersDo1.setUsername("111");
        usersDo1.setAvailable(new BigDecimal(10));
        usersDo1.setDailyLimit(new BigDecimal(2));
        usersDo1.setCurrency("y");
        when(userDao.selectById(1L)).thenReturn(usersDo1);

        UsersDo usersDo2 = new UsersDo();
        usersDo2.setId(2L);
        usersDo2.setUsername("110");
        usersDo2.setAvailable(new BigDecimal(10));
        usersDo2.setDailyLimit(new BigDecimal(2));
        usersDo2.setCurrency("y");
        when(userDao.selectByUserName(anyString())).thenReturn(usersDo2);

        doAnswer(invocation -> {
            UsersDo usersDo = invocation.getArgument(0);

            if(usersDo.getId() == 1L){
                Assert.assertTrue(usersDo.getAvailable().equals(new BigDecimal(9)));
            }


            if(usersDo.getId() == 2L){
                Assert.assertTrue(usersDo.getAvailable().equals(new BigDecimal(11)));
            }

            log.info("userId:{},available:{}", usersDo.getId(), usersDo.getAvailable());
            return null;
        }).when(userDao).save(any(UsersDo.class));


    }

}
