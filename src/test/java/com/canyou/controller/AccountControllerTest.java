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
	DataAccessException exception;
	
	@Before 
	public void setUp() throws IllegalArgumentException, IllegalAccessException{
		MockGenerator.setMock(this);
		ctrl = new AccountController();
		ctrl.accountService = service;	
		spy = spy(ctrl);
	}
	
	@Test
	public void loginGet_not_logged_Test(){
		doReturn(null).when(spy).loginAccount(session);
		assertEquals("/account/login", spy.login(session));
	}
	
	@Test
	public void loginGet_already_login_Test(){
		doReturn(mock(AccountVO.class)).when(spy).loginAccount(session);
		assertEquals("redirect:/lecture/list", spy.login(session));
	}
	
	@Test
	public void joinGetTest() {
		assertEquals("/account/join",ctrl.join());
	}
	
	@Test 
	public void login_email_not_exist_Test(){
		when(service.findByEmail(any(String.class))).thenReturn(null);
		failMessageWhen("존재하지 않는 이메일입니다.");
		Map<String, String> result = spy.login("email", "password", session);
		failMessageVerify("존재하지 않는 이메일입니다.");
		assertEquals(expect, result);
	}
	
	@Test 
	public void login_password_wrong_Test(){
		when(service.findByEmail(any(String.class))).thenReturn(account);
		when(account.getPassword()).thenReturn("pass");
		when(account.getState()).thenReturn("REG");
		failMessageWhen("존재하지 않는 비밀번호입니다.");
		Map<String, String> result = spy.login("email","password", session);
		failMessageVerify("존재하지 않는 비밀번호입니다.");
		assertEquals(expect, result);
	}
	
	@Test
	public void login_already_withdraw_Test(){
		when(service.findByEmail(any(String.class))).thenReturn(account);
		when(account.getState()).thenReturn("DEL");
		failMessageWhen("탈퇴한 이메일입니다.");
		Map<String,String> result = spy.login("email","password",session);
		failMessageVerify("탈퇴한 이메일입니다.");
		assertEquals(expect, result);
	}
	
	@Test 
	public void login_success_test(){
		when(service.findByEmail(any(String.class))).thenReturn(account);
		when(account.getState()).thenReturn("REG");
		when(account.getPassword()).thenReturn("password");
		successMessageWhen();
		Map<String,String> result = spy.login("email","password",session);
		verify(session,times(1)).setAttribute("loginAccount", account);
		successMessageVerify();
		assertEquals(expect, result);
	}
	
	@Test
	public void joinPost_not_exist_test() {
		when(service.findByEmail(any(String.class))).thenReturn(null);
		doReturn(expect).when(spy).successMessage();
		Map<String,String> result = spy.join(account);
		verify(account,times(1)).setState("REG");
		verify(service,times(1)).insert(account);
		verify(spy, times(1)).successMessage();
		assertEquals(expect, result);
	}
	
	@Test
	public void joinPost_exist_test() {
		when(service.findByEmail(any(String.class))).thenReturn(account);	
		failMessageWhen("이미 가입된 회원입니다.");
		Map<String,String> result = spy.join(account);
		failMessageVerify("이미 가입된 회원입니다.");
		assertEquals(expect, result);
	}
	
	@Test
	public void joinPost_exception_test() {
		when(service.findByEmail(any(String.class))).thenReturn(null);
		when(service.insert(account)).thenThrow(exception);
		exceptionWhen();
		Map<String,String> result = spy.join(account);
		failMessageVerify("데이터 에러");
		assertEquals(expect, result);
	}
	
	@Test
	public void useInfo_not_loggedIn_test() {
		doReturn(null).when(spy).loginAccount(session);
		assertEquals("redirect:/account/login", spy.useInfo(session));
		verify(spy,times(1)).loginAccount(session);
	}
	@Test
	public void useInfo_loggedIn_test() {
		doReturn(account).when(spy).loginAccount(session);
		assertEquals("/account/useInfo", spy.useInfo(session));
		verify(spy,times(1)).loginAccount(session);
	}
	
	@Test
	public void logout_test() {
		assertEquals("/account/login",ctrl.logout(session));
		verify(session,times(1)).invalidate();
	}
	
	@Test
	public void update_not_loggedIn_test() {
		doReturn(null).when(spy).loginAccount(session);
		assertEquals("redirect:/account/login", spy.update(session));
		verify(spy,times(1)).loginAccount(session);
	}
	
	@Test
	public void update_loggedIn_test() {
		doReturn(account).when(spy).loginAccount(session);
		assertEquals("/account/update", spy.update(session));
		verify(spy,times(1)).loginAccount(session);
	}
	
	private void successMessageVerify() {
		verify(spy,times(1)).successMessage();
	}

	private void successMessageWhen() {
		doReturn(expect).when(spy).successMessage();
	}
	
	private void exceptionWhen(){
		when(exception.getMessage()).thenReturn("데이터 에러");
		failMessageWhen("데이터 에러");
	}
	
	private void failMessageVerify(String message) {
		verify(spy,times(1)).failMessage(message);
	}

	private void failMessageWhen(String message) {
		doReturn(expect).when(spy).failMessage(message);
	}
}
