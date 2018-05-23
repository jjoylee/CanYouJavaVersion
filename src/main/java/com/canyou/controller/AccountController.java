package com.canyou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Account")
public class AccountController {
	
	@RequestMapping(value = "/Join", method = RequestMethod.GET)
	public String join() throws Exception{
		return "/account/join";
	}
}
