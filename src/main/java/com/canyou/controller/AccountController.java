package com.canyou.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.canyou.model.Account.AccountVO;

@Controller
@RequestMapping("/account")
public class AccountController extends AbstractController{

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		if(getLoginAccount(session) != null) return "redirect:/lecture/list";
		return "/account/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) 
	{
		AccountVO account = accountService.findByEmail(email);
		if(account == null) 
			return getFailMessage("�������� �ʴ� �̸����Դϴ�.");
		if(account.getState().equals("DEL"))
			return getFailMessage("Ż���� �̸����Դϴ�.");
		if(!account.getPassword().equals(password)) 
			return getFailMessage("�������� �ʴ� ��й�ȣ�Դϴ�.");
		session.setAttribute("loginAccount", account);
		return getSuccessMessage();
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "/account/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> join(AccountVO accountVO) {
		AccountVO checkExistAccount = accountService.findByEmail(accountVO.getEmail());
		if(checkExistAccount != null)
			return getFailMessage("�̹� ���Ե� ȸ���Դϴ�.");
		try{
			accountVO.setState("REG");
			accountService.insert(accountVO);
			return getSuccessMessage();
		}catch(Exception e){
			return getFailMessage(e.getMessage());
		}
	}
}
