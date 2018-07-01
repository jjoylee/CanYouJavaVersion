package com.canyou.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.canyou.model.Account.AccountVO;
import com.canyou.service.Account.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController extends AbstractController{

	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		if(loginAccount(session) != null) return "redirect:/lecture/list";
		return "/account/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> login(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session) 
	{
		AccountVO account = accountService.findByEmail(email);
		if(account == null) 
			return failMessage("�������� �ʴ� �̸����Դϴ�.");
		if(account.getState().equals("DEL"))
			return failMessage("Ż���� �̸����Դϴ�.");
		if(!account.getPassword().equals(password)) 
			return failMessage("�������� �ʴ� ��й�ȣ�Դϴ�.");
		session.setAttribute("loginAccount", account);
		return successMessage();
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
			return failMessage("�̹� ���Ե� ȸ���Դϴ�.");
		try{
			accountVO.setState("REG");
			accountService.insert(accountVO);
			return successMessage();
		}catch(Exception e){
			return failMessage(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/useInfo", method = RequestMethod.GET)
	public String useInfo(HttpSession session) {
		if(loginAccount(session) == null)
			return "redirect:/account/login";
		return "/account/useInfo";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "/account/login";
	}
}
