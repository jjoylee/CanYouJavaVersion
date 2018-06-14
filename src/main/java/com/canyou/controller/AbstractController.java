package com.canyou.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.canyou.model.Account.AccountVO;
import com.canyou.service.Account.AccountService;
import com.canyou.service.LectureDetail.LectureDetailService;

@Controller
public class AbstractController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	LectureDetailService lectureDetailService;
	
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
	
	public AccountVO getLoginAccount(HttpSession session){
		return (AccountVO)session.getAttribute("loginAccount");
	}
}
