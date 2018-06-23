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
import com.canyou.model.LectureDetail.LectureDetailVO;
import com.canyou.model.LectureType.LectureTypeVO;
import com.canyou.model.Section.SectionVO;
import com.canyou.service.LectureCategory.LectureCategoryService;
import com.canyou.service.LectureDetail.LectureDetailService;
import com.canyou.service.LectureType.LectureTypeService;
import com.canyou.service.Section.SectionService;

@Controller
@RequestMapping("/lecture")
public class LectureController extends AbstractController{
	
	@Autowired
	LectureDetailService lectureDetailService;
	
	@Autowired
	LectureCategoryService lectureCategoryService;
	
	@Autowired
	LectureTypeService lectureTypeService;
	
	@Autowired
	SectionService sectionService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, HttpSession session){
		int accountId = loginId(session);
		List<LectureDetailVO> list = lectureDetailService.findByAccountId(accountId);
		model.addAttribute("list",list);
		return "/lecture/list";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model){
		List<LectureCategoryVO> categoryList = sendCategoryListToView(model);
		List<LectureTypeVO> typeList = sendTypeListToView(model, categoryList.get(0).getId());
		sendSectionListToView(model, typeList.get(0).getId());
		return "/lecture/register";
	}

	public void sendSectionListToView(Model model, int typeId) {
		List<SectionVO> sectionList = sectionService.findByTypeId(typeId);
		model.addAttribute("sectionList",sectionList);
	}

	public List<LectureTypeVO> sendTypeListToView(Model model, int categoryId) {
		List<LectureTypeVO> typeList = lectureTypeService.findByCategoryId(categoryId);
		model.addAttribute("typeList", typeList);
		return typeList;
	}

	public List<LectureCategoryVO> sendCategoryListToView(Model model) {
		List<LectureCategoryVO> categoryList = lectureCategoryService.findAll();
		model.addAttribute("categoryList", categoryList);
		return categoryList;
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> register(LectureDetailVO lectureDetail, HttpSession session){
		if(existLectureDetail(lectureDetail.getName(), session))
			return getFailMessage("이미 존재합니다.");
		try{
			lectureDetail.setAccountId(loginId(session));
			lectureDetailService.insert(lectureDetail);
			return getSuccessMessage(); 
		}catch(Exception e){
			return getFailMessage(e.getMessage());
		}
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
	
	@RequestMapping(value = "/delete/{id}") 
	@ResponseBody
	public Map<String,String> delete(@PathVariable int id){
		try{
			lectureDetailService.delete(id);
			return getSuccessMessage();
		}catch(Exception e){
			return getFailMessage(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET) 
	public String update(@RequestParam("id") int id, Model model){
		LectureDetailVO lectureDetail = lectureDetailService.findById(id);
		model.addAttribute("lectureDetail", lectureDetail);
		List<LectureCategoryVO> categoryList = sendCategoryListToView(model);
		List<LectureTypeVO> typeList = sendTypeListToView(model, lectureDetail.getLectureCategoryId());
		sendSectionListToView(model, lectureDetail.getLectureTypeId());
		return "/lecture/update";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST) 
	@ResponseBody
	public Map<String,String> update(LectureDetailVO lectureDetail,@PathVariable("id") int id, HttpSession session){
		LectureDetailVO before = lectureDetailService.findById(lectureDetail.getId());
		String title = lectureDetail.getName();
		if (!title.equals(before.getName()) && existLectureDetail(title,session))
			return getFailMessage("이미 존재합니다.");	
		try{
			lectureDetail.setAccountId(loginId(session));
			lectureDetail.setId(id);
			lectureDetailService.update(lectureDetail);
			return getSuccessMessage();
		}catch(Exception e){
			return getFailMessage(e.getMessage());
		}
	}
	
	public boolean existLectureDetail(String title, HttpSession session){
		LectureDetailVO lectureDetail = lectureDetailService.findByAccountAndTitle(loginId(session), title);
		return (lectureDetail != null);
	}
}
