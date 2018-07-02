package com.canyou.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.canyou.model.Account.AccountVO;
import com.canyou.service.Account.AccountService;


public class AccountControllerTest{

	AccountController ctrl;
	AccountService service;
	AccountController spy;
	AccountVO account;
	HttpSession session;
	Map<String,String> expect;
	DataAccessException exception;
	JavaMailSender javaMailSender;
	MimeMessage message;
	MimeMessageHelper messageHelper;
	
	@Before 
	public void setUp() throws IllegalArgumentException, IllegalAccessException{
		MockGenerator.setMock(this);
		ctrl = new AccountController();
		ctrl.accountService = service;	
		ctrl.javaMailSender = javaMailSender;	
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
		failMessageWhen("�������� �ʴ� �̸����Դϴ�.");
		Map<String, String> result = spy.login("email", "password", session);
		failMessageVerify("�������� �ʴ� �̸����Դϴ�.");
		assertEquals(expect, result);
	}
	
	@Test 
	public void login_password_wrong_Test(){
		when(service.findByEmail(any(String.class))).thenReturn(account);
		when(account.getPassword()).thenReturn("pass");
		when(account.getState()).thenReturn("REG");
		failMessageWhen("�������� �ʴ� ��й�ȣ�Դϴ�.");
		Map<String, String> result = spy.login("email","password", session);
		failMessageVerify("�������� �ʴ� ��й�ȣ�Դϴ�.");
		assertEquals(expect, result);
	}
	
	@Test
	public void login_already_withdraw_Test(){
		when(service.findByEmail(any(String.class))).thenReturn(account);
		when(account.getState()).thenReturn("DEL");
		failMessageWhen("Ż���� �̸����Դϴ�.");
		Map<String,String> result = spy.login("email","password",session);
		failMessageVerify("Ż���� �̸����Դϴ�.");
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
		failMessageWhen("�̹� ���Ե� ȸ���Դϴ�.");
		Map<String,String> result = spy.join(account);
		failMessageVerify("�̹� ���Ե� ȸ���Դϴ�.");
		assertEquals(expect, result);
	}
	
	@Test
	public void joinPost_exception_test() {
		when(service.findByEmail(any(String.class))).thenReturn(null);
		when(service.insert(account)).thenThrow(exception);
		exceptionWhen();
		Map<String,String> result = spy.join(account);
		failMessageVerify("������ ����");
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
	public void logoutProcess_test() {
		ctrl.logoutProcess(session);
		verify(session,times(1)).invalidate();
	}
	
	@Test
	public void logout_test() {
		doNothing().when(spy).logoutProcess(session);
		assertEquals("/account/login",spy.logout(session));
		verify(spy,times(1)).logoutProcess(session);
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
		when(exception.getMessage()).thenReturn("������ ����");
		failMessageWhen("������ ����");
	}
	
	private void failMessageVerify(String message) {
		verify(spy,times(1)).failMessage(message);
	}

	private void failMessageWhen(String message) {
		doReturn(expect).when(spy).failMessage(message);
	}
	
	@Test
	public void update_fail_password_differenet_test(){
		doReturn(account).when(spy).loginAccount(session);
		when(account.getPassword()).thenReturn("PWD");
		failMessageWhen("���� ��й�ȣ�� �ٸ��ϴ�.");
		assertEquals(expect,spy.update("PW1", "PW2",session));
		verify(spy,times(1)).loginAccount(session);
		verify(account,times(1)).getPassword();
		failMessageVerify("���� ��й�ȣ�� �ٸ��ϴ�.");
	}
	@Test
	public void update_success_test(){
		doReturn(account).when(spy).loginAccount(session);
		when(account.getPassword()).thenReturn("PW");
		successMessageWhen();
		assertEquals(expect,spy.update("PW", "PW1",session));
		accountUpdateVerify();
		verify(session,times(1)).setAttribute("loginAccount", account);
		successMessageVerify();
	}

	private void accountUpdateVerify() {
		verify(spy,times(1)).loginAccount(session);
		verify(account,times(1)).getPassword();
		verify(account,times(1)).setPassword("PW1");
		verify(service,times(1)).update(account);
	}
	
	@Test
	public void update_fail_exception_test(){
		doReturn(account).when(spy).loginAccount(session);
		when(account.getPassword()).thenReturn("PW");
		exceptionWhen();
		when(service.update(account)).thenThrow(exception);
		assertEquals(expect,spy.update("PW", "PW1",session));
		accountUpdateVerify();
		failMessageVerify("������ ����");
	}
	
	@Test
	public void withdraw_success_test(){
		doReturn(account).when(spy).loginAccount(session);
		when(account.getId()).thenReturn(1);
		doNothing().when(spy).logoutProcess(session);
		successMessageWhen();
		assertEquals(expect, spy.withdraw(session));
		withdrawUpdateVerify();
		verify(spy,times(1)).logoutProcess(session);
		successMessageVerify();
	}

	private void withdrawUpdateVerify() {
		verify(spy,times(1)).loginAccount(session);
		verify(account,times(1)).getId();
		verify(service,times(1)).updateState(1, "DEL");
	}
	
	@Test
	public void withdraw_fail_exception_test(){
		doReturn(account).when(spy).loginAccount(session);
		when(account.getId()).thenReturn(1);
		exceptionWhen();
		when(service.updateState(1, "DEL")).thenThrow(exception);
		assertEquals(expect, spy.withdraw(session));
		withdrawUpdateVerify();
		failMessageVerify("������ ����");
	}
	
	@Test
	public void findPassword_get_test(){
		assertEquals("/account/findPassword",ctrl.findPassword(session));
	}
	
//	@Test
//	public void sendEmail_success_test() throws Exception{
//		when(javaMailSender.createMimeMessage()).thenReturn(message);
//		ctrl.sendEmail("you@gmail.com", "password");
//		verify(messageHelper,times(1)).setFrom("canyou@gmail.com");
//		verify(messageHelper,times(1)).setTo("you@gmail.com");
//		verify(messageHelper,times(1)).setSubject("Can You ��й�ȣ ã�� �Դϴ�.");
//		verify(messageHelper,times(1)).setText("CanYou������ ��й�ȣ�� password �Դϴ�.");
//		verify(javaMailSender,times(1)).send(message);
//	}
	
	@Test
	public void findPassword_post_fail_emailNotExist_test(){
		when(service.findByEmail("you@gmail.com")).thenReturn(null);
		failMessageWhen("�������� �ʴ� �̸����Դϴ�.");
		assertEquals(expect, spy.findPassword("you@gmail.com"));
		verify(service,times(1)).findByEmail("you@gmail.com");
		failMessageVerify("�������� �ʴ� �̸����Դϴ�.");
	}
	
	@Test
	public void findPassword_post_fail_withdraw_test(){
		when(service.findByEmail("you@gmail.com")).thenReturn(account);
		when(account.getState()).thenReturn("DEL");
		failMessageWhen("Ż���� �̸����Դϴ�.");
		assertEquals(expect, spy.findPassword("you@gmail.com"));
		verify(service,times(1)).findByEmail("you@gmail.com");
		failMessageVerify("Ż���� �̸����Դϴ�.");
	}
	
	@Test
	public void findPassword_post_fail_mailException_test() throws Exception{
		sendEmailWhen();
		exceptionWhen();
		doThrow(exception).when(spy).sendEmail("you@gmail.com", "password");
		assertEquals(expect, spy.findPassword("you@gmail.com"));
		verify(service,times(1)).findByEmail("you@gmail.com");
		verify(spy,times(1)).sendEmail("you@gmail.com", "password");
		failMessageVerify("������ ����");
	}
	
	@Test
	public void findPassword_post_success_test() throws Exception{
		sendEmailWhen();
		successMessageWhen();
		doNothing().when(spy).sendEmail("you@gmail.com", "password");
		assertEquals(expect, spy.findPassword("you@gmail.com"));
		verify(service,times(1)).findByEmail("you@gmail.com");
		verify(spy,times(1)).sendEmail("you@gmail.com", "password");
		successMessageVerify();
	}

	private void sendEmailWhen() {
		when(service.findByEmail("you@gmail.com")).thenReturn(account);
		when(account.getState()).thenReturn("REG");
		when(account.getPassword()).thenReturn("password");
	}
}
