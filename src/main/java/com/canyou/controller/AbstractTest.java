package com.canyou.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import org.junit.Before;
import org.junit.Test;

import com.canyou.service.Account.AccountService;

public class AbstractTest<T> {

	T target;
	T spy;
	
	@Before 
	public void setUp(){
		target = ((target.getClass()).getConstructor()).newInstance(null);
		service = mock(AccountService.class);
		ctrl.accountService = service;	
		spy = spy(ctrl);
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
