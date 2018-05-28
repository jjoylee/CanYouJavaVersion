package com.canyou.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView join(AccountVO account) {
		AccountVO existAccount = accountService.findByEmail(account.getEmail());
		ModelAndView modelAndView = new ModelAndView("jsonView");
		if(existAccount == null) {
			account.setState("REG");
			accountService.insert(account);
			modelAndView.addObject("result", "success");
		}
		
		return modelAndView;
	}
}
