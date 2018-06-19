package com.canyou.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import com.canyou.model.Account.AccountVO;

public class AbstractControllerTest {

	AbstractController ctrl;
	
	@Before
	public void setUp() {
		ctrl = new AbstractController();
	}
	
	@Test 
	public void getFailMessageTest(){
		String message = "msg";
		Map<String, String> result = ctrl.getFailMessage(message);
		assertEquals("fail",result.get("result"));
		assertEquals(message, result.get("message"));
	}
	
	@Test 
	public void getSucessMessageTest(){
		Map<String, String> result = ctrl.getSuccessMessage();
		assertEquals("success",result.get("result"));
	}
	
	@Test 
	public void getLoginAccount_Test(){
		HttpSession session = mock(HttpSession.class);
		AccountVO account = mock(AccountVO.class);
		when(session.getAttribute("loginAccount")).thenReturn(account);
		AccountVO result = ctrl.loginAccount(session);
		verify(session, times(1)).getAttribute("loginAccount");
		assertEquals(account,result);
	}
	
	@Test 
	public void getLoginId_Test(){
		HttpSession session = mock(HttpSession.class);
		AccountVO account = mock(AccountVO.class);
		when(session.getAttribute("loginAccount")).thenReturn(account);
		when(account.getId()).thenReturn(1);
		int result = ctrl.loginId(session);
		verify(session, times(1)).getAttribute("loginAccount");
		assertEquals(1,result);
	}
}
