package com.canyou.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.canyou.model.Account.AccountVO;
import com.canyou.service.Account.AccountService;

public class AbstractController {
	
	@Autowired
	AccountService accountService;
	
	protected AccountVO loginAccount;
	
	public Map<String,String> getFailMessage(String message) {
		Map<String,String> result = new HashMap<String,String>();
		result.put("result", "fail");
		result.put("message", message);
		return result;
	}

	public Map<String, String> getSuccessMessage() {
		Map<String,String> result = new HashMap<String,String>();
		result.put("result", "success");
		return result;
	}
}