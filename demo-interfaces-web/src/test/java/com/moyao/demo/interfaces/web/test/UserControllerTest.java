package com.moyao.demo.interfaces.web.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import com.moyao.demo.WebApplication;

import groovy.util.logging.Slf4j;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testUser(){
        String context = testRestTemplate.getForObject("/hello?id=47",String.class);
        Assert.assertEquals("Hello 47!",context);
    }
}
