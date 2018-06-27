package com.canyou.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.canyou.model.Account.AccountVO;

@Controller
public class AbstractController {
	
	public Map<String,String> failMessage(String message) {
		Map<String,String> result = new HashMap<String,String>();
		result.put("result", "fail");
		result.put("message", message);
		return result;
	}

	public Map<String, String> successMessage() {
		Map<String,String> result = new HashMap<String,String>();
		result.put("result", "success");
		return result;
	}
	
	public AccountVO loginAccount(HttpSession session){
		return (AccountVO)session.getAttribute("loginAccount");
	}
	
	public int loginId(HttpSession session){
		return ((AccountVO)session.getAttribute("loginAccount")).getId();
	}
}
