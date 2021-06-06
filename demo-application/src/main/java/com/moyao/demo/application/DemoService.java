package com.moyao.demo.application;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    private static final Logger logger = LoggerFactory.getLogger(DemoService.class);

    @PostConstruct
    public void sayHello() {
        logger.info("hello world!!");
    }
}
