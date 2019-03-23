package com.imooc.o2o.service;

import com.imooc.o2o.dto.LocalAuthExecution;
import com.imooc.o2o.entity.LocalAuth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalAuthServiceTest {
    @Autowired
    private LocalAuthService localAuthService;
    @Test
    public void testGetLocalAuthByUserNameAndPwd()
    {
        LocalAuth localAuth=localAuthService.getLocalAuthByUserNameAndPwd("shenma","123456");
        System.out.println(localAuth.toString());
    }
    @Test
    public void testAddLocalAuth()
    {
        int effectNum=localAuthService.addLocalAuth(8L,"shenma","123456");
        System.out.println(effectNum);
    }
    @Test
    public void modifyLocalAuth()
    {
        LocalAuthExecution localAuthExecution=localAuthService.modifyLocalAuth("shenma","23456","123456");
        System.out.println(localAuthExecution.getLocalAuth());
    }

}
