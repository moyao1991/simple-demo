package test.com.moyao.demo.infrastructure.db.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.moyao.demo.infra.db.dao.UserDao;
import com.moyao.demo.infra.db.model.UsersDo;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@Slf4j
public class MockTest {

    @MockBean
    protected UserDao userDao;


    @Test
    public void assertMockUser(){
        when(userDao.selectById(anyLong())).thenReturn(new UsersDo());
        UsersDo user = userDao.selectById(47L);
        Assert.assertTrue(user != null);
    }
}
