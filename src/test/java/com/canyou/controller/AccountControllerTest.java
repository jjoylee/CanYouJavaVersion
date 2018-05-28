package com.canyou.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.canyou.model.Account.AccountVO;
import com.canyou.service.Account.AccountService;


public class AccountControllerTest {

	AccountController ctrl;
	
	@Before 
	public void setUp(){
		ctrl = new AccountController();
	}
	
	@Test
	public void joinGetTest() {
		assertEquals("/Join",ctrl.join());
	}
	
	@Test
	public void joinPost_not_exist_test() {
		AccountVO account = mock(AccountVO.class);
		AccountService service = mock(AccountService.class);
		String email = "ekdnlt199@gmail.com";
		when(account.getEmail()).thenReturn(email);
		when(service.findByEmail(account.getEmail())).thenReturn(null);
		AccountVO resultVO= service.findByEmail(account.getEmail());
		assertEquals(null, resultVO);
		ctrl.accountService = service;
		ctrl.join(account);
		verify(service,times(1)).insert(account);
	}
}
