package com.canyou.controller;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

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
}
