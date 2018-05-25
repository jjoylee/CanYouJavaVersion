package com.canyou.service.Account;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.apache.tomcat.util.buf.StringCache;
import org.junit.Before;
import org.junit.Test;

import com.canyou.model.Account.AccountDao;
import com.canyou.model.Account.AccountVO;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import junit.framework.Assert;

public class AccountServiceImplTest {
	
	private AccountDao dao;
	private AccountServiceImpl service;
	
	@Before
	public void setUp(){
		dao = mock(AccountDao.class);
		service = new AccountServiceImpl();
		service.accountDao = dao;
	}
	
	@Test
	public void insertTest(){
		AccountVO vo = mock(AccountVO.class);
		when(service.insert(vo)).thenReturn(1);
		int resultVal = service.insert(vo);
		verify(dao,times(1)).insert(vo);
		assertEquals(1,resultVal);
	}
	
	@Test 
	public void updateTest(){
		AccountVO vo = new AccountVO();
		when(dao.update(vo)).thenReturn(1);
		int returnVal = service.update(vo);
		verify(dao, times(1)).update(vo);
		assertEquals(1, returnVal);
	}
	
	@Test
	public void findByEmailTest(){
		String email = "email";
		AccountVO expectObj = mock(AccountVO.class);
		when(service.findByEmail(email)).thenReturn(expectObj);
		AccountVO resultObj = service.findByEmail(email);
		verify(dao,times(1)).findByEmail(email);
		assertEquals(expectObj, resultObj);
	}
	
	@Test
	public void updateStateTest(){
		int accountId = any(Integer.class);
		String state = any(String.class);
		when(service.updateState(accountId, state)).thenReturn(1);
		int resultVal = service.updateState(accountId, state);
		verify(dao, times(1)).updateState(accountId, state);
		assertEquals(1,resultVal);
	}
}
