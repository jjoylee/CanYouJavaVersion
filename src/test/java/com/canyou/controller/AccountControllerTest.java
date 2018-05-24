package com.canyou.controller;

import static org.junit.Assert.*;

import org.junit.Test;


public class AccountControllerTest {

	@Test
	public void join_type_is_j_Test() {
		AccountController ctrl = new AccountController();
		assertEquals("/account/junghee", ctrl.join("j"));
	}
	
	@Test
	public void join_type_is_not_j_Test() {
		AccountController ctrl = new AccountController();
		assertEquals("/account/join", ctrl.join("k"));
	}
	
}
