package com.moyao.demo.infra.rpc.db.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.moyao.demo.infra.rpc.db.JdbcApplication;
import com.moyao.demo.infra.db.dao.TradeDetailDao;
import com.moyao.demo.infra.db.dao.UserDao;
import com.moyao.demo.infra.rpc.db.config.SqlAuditInnerInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * 用于sql review 时执行, 提取sql
 * @see SqlAuditInnerInterceptor
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = JdbcApplication.class)
@ActiveProfiles("audit")
@Slf4j
public class SqlReviewPrintTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TradeDetailDao tradeDetailDao;

    @Test
    public void printReviewSql(){
        userDao.selectById(47L);
        tradeDetailDao.selectById(1L, 25L);
    }
}
