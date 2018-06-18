package com.canyou.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.canyou.model.LectureCategory.LectureCategoryVO;
import com.canyou.model.LectureDetail.LectureDetailVO;
import com.canyou.model.LectureType.LectureTypeVO;
import com.canyou.model.Section.SectionVO;

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
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model){
		List<LectureCategoryVO> categoryList = lectureCategoryService.findAll();
		model.addAttribute("categoryList", categoryList);
		List<LectureTypeVO> typeList = lectureTypeService.findByCategoryId(categoryList.get(0).getId());
		model.addAttribute("typeList", typeList);
		List<SectionVO> sectionList = sectionService.findByTypeId(typeList.get(0).getId());
		model.addAttribute("sectionList",sectionList);
		return "/lecture/register";
	}
	
	@RequestMapping(value = "/typePartial")
	public String typePartial(Model model, @RequestParam("categoryId") int categoryId){
		List<LectureTypeVO> typeList = lectureTypeService.findByCategoryId(categoryId);
        model.addAttribute("typeList",typeList);
		return "/lecture/typePartial";
	}
	
	@RequestMapping(value="/sectionPartial")
	public String sectionPartial(Model model, @RequestParam("typeId") int typeId){
		List<SectionVO> sectionList = sectionService.findByTypeId(typeId);
		model.addAttribute("sectionList",sectionList);
		return "/lecture/sectionPartial";
	}
}
