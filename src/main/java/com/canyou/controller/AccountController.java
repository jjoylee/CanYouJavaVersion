package com.canyou.controller;

import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
	
	@Autowired
	JavaMailSender javaMailSender;
	
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
			return failMessage("존재하지 않는 이메일입니다.");
		if(account.getState().equals("DEL"))
			return failMessage("탈퇴한 이메일입니다.");
		if(!account.getPassword().equals(password)) 
			return failMessage("존재하지 않는 비밀번호입니다.");
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
			return failMessage("이미 가입된 회원입니다.");
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
		logoutProcess(session);
		return "/account/login";
	}

	public void logoutProcess(HttpSession session) {
		session.invalidate();
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpSession session) {
		if(loginAccount(session) == null)
			return "redirect:/account/login";
		return "/account/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> update(@RequestParam("currentPwd") String currentPwd, @RequestParam("newPassword") String newPassword, HttpSession session) {
		AccountVO account = loginAccount(session);
		if(!account.getPassword().equals(currentPwd))
			return failMessage("현재 비밀번호가 다릅니다.");
		try{
			account.setPassword(newPassword);
			accountService.update(account);
			session.setAttribute("loginAccount", account);
			return successMessage();
		}catch(Exception e){
			return failMessage(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/withdraw", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> withdraw(HttpSession session) {
		try{
			AccountVO account = loginAccount(session);
			accountService.updateState(account.getId(),"DEL");
			logoutProcess(session);
			return successMessage();
		}catch(Exception e){
			return failMessage(e.getMessage());
		}
	}
	
	public void sendEmail(String email, String password) throws Exception{
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
		messageHelper.setTo(email);
		messageHelper.setSubject("Can You 비밀번호 찾기 입니다.");
		String text = "CanYou에서의 비밀번호는 " + password + " 입니다.";
		messageHelper.setText(text);
		javaMailSender.send(message);
	}
	
	@RequestMapping(value = "/findPassword", method = RequestMethod.GET)
	public String findPassword (HttpSession session) {
		return "/account/findPassword";
	}
	
	@RequestMapping(value = "/findPassword", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> findPassword (@RequestParam("email") String email) {
		AccountVO account = accountService.findByEmail(email);
		if(account == null)
			return failMessage("존재하지 않는 이메일입니다.");
		if(account.getState().equals("DEL"))
			return failMessage("탈퇴한 이메일입니다.");
		try{
			sendEmail(email,account.getPassword());
			return successMessage();
		}catch(Exception e){
			System.out.println("여기임?");
			return failMessage(e.getMessage());
		}
	}
}
