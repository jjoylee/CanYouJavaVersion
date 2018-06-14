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

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;

import com.canyou.model.Account.AccountVO;
import com.canyou.service.Account.AccountService;


public class AccountControllerTest {

	AccountController ctrl;
	AccountService service;
	AccountController spy;
	AccountVO account;
	HttpSession session;
	Map<String,String> expect;
	
	@Before 
	public void setUp(){
		ctrl = new AccountController();
		service = mock(AccountService.class);
		ctrl.accountService = service;	
		spy = spy(ctrl);
		account = mock(AccountVO.class);
		session = mock(HttpSession.class);
		expect = mock(Map.class);
	}
	
	@Test
	public void loginGet_not_logged_Test(){
		doReturn(null).when(spy).getLoginAccount(session);
		assertEquals("/account/login", spy.login(session));
	}
	
	@Test
	public void loginGet_already_login_Test(){
		doReturn(mock(AccountVO.class)).when(spy).getLoginAccount(session);
		assertEquals("redirect:/lecture/list", spy.login(session));
	}
	
	@Test
	public void joinGetTest() {
		assertEquals("/account/join",ctrl.join());
	}
	
	@Test 
	public void login_email_not_exist_Test(){
		when(service.findByEmail(any(String.class))).thenReturn(null);
		doReturn(expect).when(spy).getFailMessage("존재하지 않는 이메일입니다.");
		Map<String, String> result = spy.login("email", "password", session);
		verify(spy, times(1)).getFailMessage("존재하지 않는 이메일입니다.");
		assertEquals(expect, result);
	}
	
	@Test 
	public void login_password_wrong_Test(){
		when(service.findByEmail(any(String.class))).thenReturn(account);
		when(account.getPassword()).thenReturn("pass");
		when(account.getState()).thenReturn("REG");
		doReturn(expect).when(spy).getFailMessage("존재하지 않는 비밀번호입니다.");
		Map<String, String> result = spy.login("email","password", session);
		verify(spy, times(1)).getFailMessage("존재하지 않는 비밀번호입니다.");
		assertEquals(expect, result);
	}
	
	@Test
	public void login_already_withdraw_Test(){
		when(service.findByEmail(any(String.class))).thenReturn(account);
		when(account.getState()).thenReturn("DEL");
		doReturn(expect).when(spy).getFailMessage("탈퇴한 이메일입니다.");
		Map<String,String> result = spy.login("email","password",session);
		verify(spy, times(1)).getFailMessage("탈퇴한 이메일입니다.");
		assertEquals(expect, result);
	}
	
	@Test 
	public void login_success_test(){
		when(service.findByEmail(any(String.class))).thenReturn(account);
		when(account.getState()).thenReturn("REG");
		when(account.getPassword()).thenReturn("password");
		doReturn(expect).when(spy).getSuccessMessage();
		Map<String,String> result = spy.login("email","password",session);
		verify(session,times(1)).setAttribute("loginAccount", account);
		verify(spy,times(1)).getSuccessMessage();
		assertEquals(expect, result);
	}
	
	@Test
	public void joinPost_not_exist_test() {
		when(service.findByEmail(any(String.class))).thenReturn(null);
		doReturn(expect).when(spy).getSuccessMessage();
		Map<String,String> result = spy.join(account);
		verify(account,times(1)).setState("REG");
		verify(service,times(1)).insert(account);
		verify(spy, times(1)).getSuccessMessage();
		assertEquals(expect, result);
	}
	
	@Test
	public void joinPost_exist_test() {
		when(service.findByEmail(any(String.class))).thenReturn(account);	
		doReturn(expect).when(spy).getFailMessage("이미 가입된 회원입니다.");
		Map<String,String> result = spy.join(account);
		verify(spy, times(1)).getFailMessage("이미 가입된 회원입니다.");
		assertEquals(expect, result);
	}
	
	@Test
	public void joinPost_exception_test() {
		when(service.findByEmail(any(String.class))).thenReturn(null);
		DataAccessException exception = mock(DataAccessException.class);
		when(service.insert(account)).thenThrow(exception);
		when(exception.getMessage()).thenReturn("데이터에러");
		doReturn(expect).when(spy).getFailMessage("데이터에러");
		Map<String,String> result = spy.join(account);
		verify(spy, times(1)).getFailMessage("데이터에러");
		assertEquals(expect, result);
	}
}
