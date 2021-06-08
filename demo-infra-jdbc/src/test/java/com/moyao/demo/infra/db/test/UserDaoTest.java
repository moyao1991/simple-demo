package com.moyao.demo.infra.db.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.moyao.demo.infra.db.dao.UserDao;
import com.moyao.demo.infra.db.model.UsersDo;
import com.moyao.demo.infra.db.JdbcApplication;

import lombok.extern.slf4j.Slf4j;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = JdbcApplication.class)
@Slf4j
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void assertUser(){
        UsersDo user = userDao.selectById(1L);
        Assert.assertTrue(user != null);
        log.info("userId:{}", user.getId());
    }
}
