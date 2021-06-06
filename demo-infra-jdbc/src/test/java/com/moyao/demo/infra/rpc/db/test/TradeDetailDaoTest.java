package com.moyao.demo.infra.rpc.db.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.moyao.demo.infra.rpc.db.JdbcApplication;
import com.moyao.demo.infra.db.dao.TradeDetailDao;
import com.moyao.demo.infra.db.model.TradeDetailDo;

import lombok.extern.slf4j.Slf4j;


@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = JdbcApplication.class)
@Slf4j
public class TradeDetailDaoTest {

    @Autowired
    private TradeDetailDao tradeDetailDao;

    @Test
    public void assertTradeDetail(){
        TradeDetailDo tradeDetailDo = tradeDetailDao.selectById(1L, 25L);
        Assert.assertTrue(tradeDetailDo != null);
        log.info("tid:{}", tradeDetailDo.getTid());
    }
}
