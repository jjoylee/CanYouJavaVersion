package com.canyou.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.canyou.model.Result.ResultVO;
import com.canyou.service.LectureCategoryRequirement.LectureCategoryRequirementService;
import com.canyou.service.LectureDetail.LectureDetailService;
import com.canyou.service.LectureTypeRequirement.LectureTypeRequirementService;
import com.canyou.service.Result.ResultService;
import com.canyou.service.ScoreRequirement.ScoreRequirementService;
import com.canyou.service.Section.SectionService;
import com.canyou.service.SectionRequirement.SectionRequirementService;

@Controller
@RequestMapping("/result")
public class ResultController extends AbstractController{
	
	@Autowired
	ResultService resultService;
	
	@Autowired
	LectureCategoryRequirementService categoryRequirementService;
	
	@Autowired
	LectureTypeRequirementService typeRequirementService;
	
	@Autowired
	ScoreRequirementService scoreRequirementService;
	
	@Autowired
	SectionRequirementService sectionRequirementService;
	
	@Autowired
	SectionService sectionService;
	
	@Autowired
	LectureDetailService detailService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpSession session, Model model) {
		setResultService(session);
		List<ResultVO> results = resultService.getResults();
		model.addAttribute("results", results);
		model.addAttribute("isAllPassed", isAllPassed(results));
		return "/result/index";
	}

	public void setResultService(HttpSession session) {
		int accountId = loginId(session);
		resultService.setCategoryRequirements(categoryRequirementService.findByAccountId(accountId));
		resultService.setLectureDetails(detailService.findByAccountId(accountId));
		resultService.setScoreRequirement(scoreRequirementService.findByAccountIdForCheck(accountId));
		resultService.setSectionRequirement(sectionRequirementService.findByAccountIdForCheck(accountId));
		resultService.setSections(sectionService.findAll());
		resultService.setTypeRequirements(typeRequirementService.findByAccountId(accountId));
	}
	
	public boolean isAllPassed(List<ResultVO> results){
		if(results.size()==0) return false;
		for(ResultVO result : results){
			if(!result.isPassed()) return false;
		}
		return true;
	}
}
