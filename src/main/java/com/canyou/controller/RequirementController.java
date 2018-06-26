package com.canyou.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.canyou.model.LectureCategory.LectureCategoryVO;
import com.canyou.model.LectureCategoryRequirement.LectureCategoryRequirementVO;
import com.canyou.model.LectureTypeRequirement.LectureTypeRequirementVO;
import com.canyou.service.LectureCategory.LectureCategoryService;
import com.canyou.service.LectureCategoryRequirement.LectureCategoryRequirementService;
import com.canyou.service.LectureType.LectureTypeService;
import com.canyou.service.LectureTypeRequirement.LectureTypeRequirementService;

@Controller
@RequestMapping("/requirement")
public class RequirementController extends AbstractController{
	
	@Autowired 
	LectureCategoryRequirementService categoryRequirementService;
	
	@Autowired 
	LectureCategoryService categoryService;
	
	@Autowired 
	LectureTypeRequirementService typeRequirementService;
	
	@Autowired 
	LectureTypeService typeService;
	
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
	
	@RequestMapping(value = "/categoryRegister", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> categoryRegister(LectureCategoryRequirementVO requirement, HttpSession session){
		if(existCategoryRequirement(requirement.getLectureCategoryId(),session))
			return getFailMessage("이미 존재합니다.");
		try{
			requirement.setAccountId(loginId(session));
			categoryRequirementService.insert(requirement);
			return getSuccessMessage();
		}catch(Exception e){
			return getFailMessage(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/categoryDelete/{id}")
	@ResponseBody
	public Map<String, String> categoryDelete(@PathVariable("id")int id){
		try{
			categoryRequirementService.delete(id);
			return getSuccessMessage();
		}catch(Exception e){
			return getFailMessage(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/categoryUpdate", method=RequestMethod.GET)
	public String categoryUpdate(@RequestParam("id") int id, Model model, HttpSession session){
		LectureCategoryRequirementVO requirement = categoryRequirementService.findById(id);
		List<LectureCategoryVO> list = categoryService.findAll();
		model.addAttribute("categoryList",list);
		model.addAttribute("requirement",requirement);
		return "/requirement/categoryUpdate";
	}
	
	@RequestMapping(value = "/categoryUpdate/{id}", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> categoryUpdate(@PathVariable("id") int id, LectureCategoryRequirementVO requirement, HttpSession session){
		if(updatedCategoryRequirementExist(id, requirement, session))
			return getFailMessage("이미 존재합니다.");
		try{
			requirement.setAccountId(loginId(session));
			requirement.setId(id);
			categoryRequirementService.update(requirement);
			return getSuccessMessage();
		}catch(Exception e){
			return getFailMessage(e.getMessage());
		}
	}
	
	public boolean updatedCategoryRequirementExist(int id, LectureCategoryRequirementVO requirement, HttpSession session){
		LectureCategoryRequirementVO beforeRequirement = categoryRequirementService.findById(id);
		int beforeCategoryId = beforeRequirement.getLectureCategoryId();
		int requirementId = requirement.getLectureCategoryId();
		return (beforeCategoryId != requirementId && existCategoryRequirement(requirementId,session));
	}
	
	public boolean existCategoryRequirement(int id, HttpSession session){
		int accountId = loginId(session);
		LectureCategoryRequirementVO requirement = categoryRequirementService.findByAccountAndCategoryId(accountId, id);
		return (requirement != null);
	}
	
	@RequestMapping(value = "/type", method = RequestMethod.GET)
	public String type(Model model, HttpSession session){
		List<LectureTypeRequirementVO> requirements = typeRequirementService.findByAccountId(loginId(session));
		model.addAttribute("list", requirements);
		return "/requirement/type";
	}
}
