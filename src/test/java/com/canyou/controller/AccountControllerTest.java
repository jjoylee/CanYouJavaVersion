package com.canyou.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;

import com.canyou.model.Account.AccountVO;
import com.canyou.service.Account.AccountService;


public class AccountControllerTest {

	AccountController ctrl;
	AccountService service;
	
	@Before 
	public void setUp(){
		ctrl = new AccountController();
		service = mock(AccountService.class);
		ctrl.accountService = service;	
	}
	
	@Test
	public void joinGetTest() {
		assertEquals("/account/join",ctrl.join());
	}
	
	@Test
	public void joinPost_not_exist_test() {
		AccountController spy = spy(ctrl);
		AccountVO account = mock(AccountVO.class);
		when(service.findByEmail(any(String.class))).thenReturn(null);
		Map<String,String> expect = mock(Map.class);
		doReturn(expect).when(spy).getSuccessMessage();
		Map<String,String> result = spy.join(account);
		verify(account,times(1)).setState("REG");
		verify(service,times(1)).insert(account);
		verify(spy, times(1)).getSuccessMessage();
		assertEquals(expect, result);
	}
	
	@Test
	public void joinPost_exist_test() {
		AccountController spy = spy(ctrl);
		AccountVO account = mock(AccountVO.class);
		when(service.findByEmail(any(String.class))).thenReturn(account);	
		Map<String,String> expect = mock(Map.class);
		doReturn(expect).when(spy).getFailMessage("이미 가입된 회원입니다.");
		Map<String,String> result = spy.join(account);
		verify(spy, times(1)).getFailMessage("이미 가입된 회원입니다.");
		assertEquals(expect, result);
	}
	
	@Test
	public void joinPost_exception_test() {
		AccountController spy = spy(ctrl);
		AccountVO account = mock(AccountVO.class);
		when(service.findByEmail(any(String.class))).thenReturn(null);
		DataAccessException exception = mock(DataAccessException.class);
		when(service.insert(account)).thenThrow(exception);
		when(exception.getMessage()).thenReturn("데이터에러");
		Map<String,String> expect = mock(Map.class);
		doReturn(expect).when(spy).getFailMessage("데이터에러");
		Map<String,String> result = spy.join(account);
		verify(spy, times(1)).getFailMessage("데이터에러");
		assertEquals(expect, result);
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
	
}
