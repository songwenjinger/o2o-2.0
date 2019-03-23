package com.imooc.o2o.dao;

import com.imooc.o2o.dao.LocalAuthDao;
import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.util.MD5;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalAuthDaoTest {

	@Autowired
    LocalAuthDao localAuthDao;
	
	@Test
	public void testInsertLocalAuth() {
		
		LocalAuth localAuth = new LocalAuth();
		PersonInfo personInfo=new PersonInfo();
		personInfo.setUserId(9L);
		localAuth.setPersonInfo(personInfo);
		localAuth.setUserName("huhua");
		localAuth.setPassword("123456");
		localAuth.setCreateTime(new Date());
		localAuth.setLastEditTime(new Date());
		int effectNum = localAuthDao.insertLocalAuth(localAuth);
		Assert.assertEquals(1, effectNum);

	}
	@Test
	public void testQueryLocalByUserNameAndPassword()throws Exception{
		//按照账号和密码查询信息
		LocalAuth localAuth=localAuthDao.queryLocalByUserNameAndPwd("huahua","123456");
		System.out.println(localAuth.toString());
	}
	@Test
	public void testQueryByUserId()
	{
		//按照用户Id查询平台账号，进而获取用户信息
		LocalAuth localAuth=localAuthDao.queryLocalByUserId(11L);
		System.out.println(localAuth.toString());
	}
	@Test
	public void testUpdate()
	{
		Date now=new Date();
		//更改密码的措施
		int effectNum=localAuthDao.updateLocalAuth("shenma", "s05bse6q2qlb9qblls96s592y55y556s","bsebse6559bbq05e5s5l02b50lssl6yy",now);
		System.out.println(MD5.getMd5("23456"));

		System.out.println(MD5.getMd5("123456"));
		String s=MD5.getMd5("23456");
		System.out.println(s);
		System.out.println(effectNum);
	}
}
