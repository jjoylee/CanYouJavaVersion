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
import com.canyou.model.LectureType.LectureTypeVO;
import com.canyou.model.LectureTypeRequirement.LectureTypeRequirementVO;
import com.canyou.model.ScoreRequirement.ScoreRequirementVO;
import com.canyou.model.SectionRequirement.SectionRequirementVO;
import com.canyou.service.LectureCategory.LectureCategoryService;
import com.canyou.service.LectureCategoryRequirement.LectureCategoryRequirementService;
import com.canyou.service.LectureType.LectureTypeService;
import com.canyou.service.LectureTypeRequirement.LectureTypeRequirementService;
import com.canyou.service.ScoreRequirement.ScoreRequirementService;
import com.canyou.service.SectionRequirement.SectionRequirementService;

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
	
	@Autowired 
	SectionRequirementService sectionRequirementService;

	@Autowired 
	ScoreRequirementService scoreRequirementService;
	
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
			return failMessage("이미 존재합니다.");
		try{
			requirement.setAccountId(loginId(session));
			categoryRequirementService.insert(requirement);
			return successMessage();
		}catch(Exception e){
			return failMessage(e.getMessage());
		}
	}
	
	public boolean isAuthorizedCategory(int id, HttpSession session){
		LectureCategoryRequirementVO requirement = categoryRequirementService.findById(id);
		return requirement.getAccountId() == loginId(session);
	}
	
	@RequestMapping(value = "/categoryDelete/{id}")
	@ResponseBody
	public Map<String, String> categoryDelete(@PathVariable("id")int id, HttpSession session){
		if(!isAuthorizedCategory(id,session))
			return failMessage("접근 불가능한 페이지입니다.");
		try{
			categoryRequirementService.delete(id);
			return successMessage();
		}catch(Exception e){
			return failMessage(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/categoryUpdate", method=RequestMethod.GET)
	public String categoryUpdate(@RequestParam("id") int id, Model model, HttpSession session){
		if(!isAuthorizedCategory(id, session))
			return "redirect:/requirement/category";
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
			return failMessage("이미 존재합니다.");
		try{
			requirement.setAccountId(loginId(session));
			requirement.setId(id);
			categoryRequirementService.update(requirement);
			return successMessage();
		}catch(Exception e){
			return failMessage(e.getMessage());
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
	
	@RequestMapping(value = "/typeRegister", method = RequestMethod.GET)
	public String typeRegister(Model model){
		List<LectureCategoryVO> categoryList = categoryService.findLectureTypeExist();
		List<LectureTypeVO> typeList = typeService.findByCategoryId(categoryList.get(0).getId());
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("typeList", typeList);
		return "/requirement/typeRegister";
	}
	
	public boolean existTypeRequirement(int typeId, HttpSession session){
		int accountId = loginId(session);
		LectureTypeRequirementVO requirement = typeRequirementService.findByAccountAndTypeId(accountId, typeId);
		return (requirement != null);
	}
	
	@RequestMapping(value = "/typeRegister", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> typeRegister(LectureTypeRequirementVO requirement, HttpSession session){
		if(existTypeRequirement(requirement.getLectureTypeId(), session))
			return failMessage("이미 존재합니다.");
		try{
			requirement.setAccountId(loginId(session));
			typeRequirementService.insert(requirement);
			return successMessage();
		}catch(Exception e){
			return failMessage(e.getMessage());
		}
	}
	
	public boolean isAuthorizedType(int id, HttpSession session){
		LectureTypeRequirementVO requirement = typeRequirementService.findById(id);
		return requirement.getAccountId() == loginId(session);
	}
	
	@RequestMapping(value = "/typeDelete/{id}")
	@ResponseBody
	public Map<String,String> typeDelete(@PathVariable("id")int id, HttpSession session){
		if(!isAuthorizedType(id,session))
			return failMessage("접근 불가능한 페이지입니다.");
		try{
			typeRequirementService.delete(id);
			return successMessage();
		}catch(Exception e){
			return failMessage(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/typeUpdate", method = RequestMethod.GET)
	public String typeUpdate(@RequestParam("id") int id, HttpSession session, Model model){
		if(!isAuthorizedType(id,session))
			return "redirect:/requirement/type";
		LectureTypeRequirementVO requirement = typeRequirementService.findById(id);
		List<LectureCategoryVO> categoryList = categoryService.findLectureTypeExist();
		List<LectureTypeVO> typeList = typeService.findByCategoryId(requirement.getLectureCategoryId());
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("typeList", typeList);
		model.addAttribute("requirement",requirement);
		return "/requirement/typeUpdate";
	}
	
	@RequestMapping(value = "/typeUpdate/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> typeUpdate(@PathVariable("id") int id, HttpSession session, LectureTypeRequirementVO requirement){
		if(updatedTypeExist(id,requirement,session))
			return failMessage("이미 존재합니다.");
		try{
			requirement.setAccountId(loginId(session));
			requirement.setId(id);
			typeRequirementService.update(requirement);
			return successMessage();
		}catch(Exception e){
			return failMessage(e.getMessage());
		}
	}

	public boolean updatedTypeExist(int id, LectureTypeRequirementVO requirement, HttpSession session) {
		LectureTypeRequirementVO before = typeRequirementService.findById(id);
		int typeId = requirement.getLectureTypeId();
		return (before.getLectureTypeId() != typeId && existTypeRequirement(typeId,session));
	}
	
	@RequestMapping(value = "/section", method = RequestMethod.GET)
	public String section( HttpSession session, Model model){
		List<SectionRequirementVO> list = sectionRequirementService.findByAccountId(loginId(session));
		model.addAttribute("list",list);
		return "/requirement/section";
	}
	
	@RequestMapping(value = "/sectionRegister", method = RequestMethod.GET)
	public String sectionRegister(HttpSession session){
		if(existSectionRequirement(loginId(session)))
			return "redirect:/requirement/section";
		return "/requirement/sectionRegister";
	}
	
	public boolean existSectionRequirement(int accountId){
		return (sectionRequirementService.findByAccountIdForCheck(accountId) != null);
	}
	
	@RequestMapping(value = "/sectionRegister", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> sectionRegister(SectionRequirementVO requirement ,HttpSession session){
		try{
			requirement.setAccountId(loginId(session));
			requirement.setLectureTypeId(2);
			sectionRequirementService.insert(requirement);
			return successMessage();
		}catch(Exception e){
			return failMessage(e.getMessage());
		}
	}
	
	public boolean isAuthorizedSection(int id, HttpSession session){
		SectionRequirementVO requirement = sectionRequirementService.findById(id);
		return requirement.getAccountId() == loginId(session);
	}
	
	@RequestMapping(value = "/sectionUpdate", method = RequestMethod.GET)
	public String sectionUpdate(@RequestParam("id") int id ,HttpSession session, Model model){
		if(!isAuthorizedSection(id,session))
			return "redirect:/requirement/section";
		SectionRequirementVO requirement = sectionRequirementService.findById(id);
		model.addAttribute("requirement",requirement);
		return "/requirement/sectionUpdate";
	}
	
	@RequestMapping(value = "/sectionUpdate/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> sectionUpdate(@PathVariable("id") int id, SectionRequirementVO requirement ,HttpSession session){
		try{
			requirement.setId(id);
			requirement.setAccountId(loginId(session));
			requirement.setLectureTypeId(2);
			sectionRequirementService.update(requirement);
			return successMessage();
		}catch(Exception e){
			return failMessage(e.getMessage());
		}
	}
	
	@RequestMapping(value="/sectionDelete/{id}")
	@ResponseBody
	public Map<String,String> sectionDelete(@PathVariable("id") int id, HttpSession session){
		if(!isAuthorizedSection(id,session))
			return failMessage("접근 불가능한 페이지입니다.");
		try{
			sectionRequirementService.delete(id);
			return successMessage();
		}catch(Exception e){
			return failMessage(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/score", method = RequestMethod.GET)
	public String score( HttpSession session, Model model){
		List<ScoreRequirementVO> list = scoreRequirementService.findByAccountId(loginId(session));
		model.addAttribute("list",list);
		return "/requirement/score";
	}
}
