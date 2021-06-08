package com.moyao.demo.interfaces.test;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;
import com.moyao.demo.DemoApplication;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@Slf4j
public class I18nTest {

    @Autowired
    private MessageSource messageSource;

    @Test
    public void assertMessage(){
        String enMsg = messageSource.getMessage("welcome", new Object[]{"n"}, Locale.US);
        String msg = messageSource.getMessage("welcome", new Object[]{"n"}, Locale.getDefault());
        Assert.assertEquals("您好 n，欢迎你！",msg);
        Assert.assertEquals("hello n, welcome !",enMsg);

    }
}
