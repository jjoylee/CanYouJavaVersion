package com.canyou.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.canyou.model.LectureCategory.LectureCategoryVO;
import com.canyou.model.LectureCategoryRequirement.LectureCategoryRequirementVO;
import com.canyou.service.LectureCategory.LectureCategoryService;
import com.canyou.service.LectureCategoryRequirement.LectureCategoryRequirementService;

@Controller
@RequestMapping("/requirement")
public class RequirementController extends AbstractController{
	
	@Autowired 
	LectureCategoryRequirementService categoryRequirementService;
	
	@Autowired 
	LectureCategoryService categoryService;
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String category(Model model, HttpSession session){
		int accountId = loginId(session);
		List<LectureCategoryRequirementVO> list = categoryRequirementService.findByAccountId(accountId);
		model.addAttribute("list", list);
		return "/requirement/category";
	}
	
	@RequestMapping(value = "/categoryRegister", method = RequestMethod.GET)
	public String categoryRegister(Model model){
		List<LectureCategoryVO> list = categoryService.findAll();
		model.addAttribute("list",list);
		return "/requirement/categoryRegister";
	}
	
	@RequestMapping(value = "/categoryRegister", method = RequestMethod.PUT)
	@ResponseBody
	public String categoryRegister(LectureCategoryRequirementVO requirement){
		return "/requirement/categoryRegister";
	}
	
	public boolean existCategoryRequirement(int id, HttpSession session){
		int accountId = loginId(session);
		LectureCategoryRequirementVO requirement = categoryRequirementService.findByAccountAndCategoryId(accountId, id);
		return (requirement != null);
	}
}
