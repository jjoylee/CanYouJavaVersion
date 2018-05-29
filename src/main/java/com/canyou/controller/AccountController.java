package com.canyou.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.canyou.model.Account.AccountVO;
import com.canyou.service.Account.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "/account/login";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "/account/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> join(AccountVO account) {
		AccountVO existAccount = accountService.findByEmail(account.getEmail());
		if(existAccount != null)
			return getFailMessage("이미 가입된 회원입니다.");
		try{
			account.setState("REG");
			accountService.insert(account);
			return getSuccessMessage();
		}catch(Exception e){
			return getFailMessage(e.getMessage());
		}
	}

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
