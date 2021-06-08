package com.moyao.demo.application.test;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.moyao.demo.DemoApplication;
import com.moyao.demo.application.TransferService;
import com.moyao.demo.common.cmd.TransferCommand;
import com.moyao.demo.infra.db.dao.UserDao;
import com.moyao.demo.infra.db.model.UsersDo;
import com.moyao.demo.infra.rpc.extobj.ExchangeRateEo;
import com.moyao.demo.infra.rpc.extservice.ExchangeRateExService;

import lombok.extern.slf4j.Slf4j;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ActiveProfiles({"jdbc","rpc"})
@Slf4j
public class TransferServiceTest {

    @Autowired
    private TransferService transferService;

    @Autowired
    private UserDao userDao;

    @Test
    public void assertTransfer(){
        TransferCommand transferCommand = new TransferCommand();
        transferCommand.setSourceUserId(1L);
        transferCommand.setTargetAccountNumber("110");
        transferCommand.setTargetCurrency("y");
        transferCommand.setTargetAmount(new BigDecimal(1));
        transferService.transfer(transferCommand);

        UsersDo usersDo = userDao.selectById(1L);
        Assert.assertTrue(usersDo != null);
        Assert.assertTrue(usersDo.getAvailable().compareTo(new BigDecimal(9L)) == 0);
    }
}
