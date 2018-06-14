package com.canyou.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.canyou.model.LectureDetail.LectureDetailVO;

@Controller
@RequestMapping("/lecture")
public class LectureController extends AbstractController{
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, HttpSession session){
		int accountId = getLoginAccount(session).getId();
		List<LectureDetailVO> list = lectureDetailService.findByAccountId(accountId);
		model.addAttribute("list",list);
		return "/lecture/list";
	} 
}
