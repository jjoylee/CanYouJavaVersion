package com.canyou.service.Result;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.canyou.model.LectureCategoryRequirement.LectureCategoryRequirementVO;
import com.canyou.model.LectureDetail.LectureDetailVO;
import com.canyou.model.LectureTypeRequirement.LectureTypeRequirementVO;
import com.canyou.model.Result.ResultVO;
import com.canyou.model.ScoreRequirement.ScoreRequirementVO;
import com.canyou.model.Section.SectionVO;
import com.canyou.model.SectionRequirement.SectionRequirementVO;

@Service
public class ResultServiceImpl implements ResultService{
	
	private List<LectureCategoryRequirementVO> categoryRequirements;
	private List<LectureTypeRequirementVO> typeRequirements;
	private ScoreRequirementVO scoreRequirement;
	private SectionRequirementVO sectionRequirement;
	private List<LectureDetailVO> lectureDetails;
	private List<SectionVO> sections;
	
	public List<LectureCategoryRequirementVO> getCategoryRequirements() {
		return categoryRequirements;
	}
	public void setCategoryRequirements(List<LectureCategoryRequirementVO> categoryRequirements) {
		this.categoryRequirements = categoryRequirements;
	}
	public List<LectureTypeRequirementVO> getTypeRequirements() {
		return typeRequirements;
	}
	public void setTypeRequirements(List<LectureTypeRequirementVO> typeRequirements) {
		this.typeRequirements = typeRequirements;
	}
	public ScoreRequirementVO getScoreRequirement() {
		return scoreRequirement;
	}
	public void setScoreRequirement(ScoreRequirementVO scoreRequirement) {
		this.scoreRequirement = scoreRequirement;
	}
	public SectionRequirementVO getSectionRequirement() {
		return sectionRequirement;
	}
	public void setSectionRequirement(SectionRequirementVO sectionRequirement) {
		this.sectionRequirement = sectionRequirement;
	}
	public List<SectionVO> getSections() {
		return sections;
	}
	public void setSections(List<SectionVO> sections) {
		this.sections = sections;
	}
	public List<LectureDetailVO> getLectureDetails() {
		return lectureDetails;
	}
	public void setLectureDetails(List<LectureDetailVO> lectureDetails) {
		this.lectureDetails = lectureDetails;
	}
	
	public List<ResultVO> getResults(){
		List<ResultVO> results = new ArrayList<ResultVO>();
		addCategoryRequirement(results);
		addTypeRequirement(results);
		addSectionRequirement(results);
		addScoreRequirement(results);
		return results;
	}

	private void addScoreRequirement(List<ResultVO> results) {
		if(scoreRequirement == null) return;
		ResultVO result = new ResultVO();
		result.setName("총학점");
		result.setRequirement(scoreRequirement.getCutline());
		result.setScore(getTotalCredit());
		results.add(result);
	}
	
	private int getTotalCredit() {
		int credit = 0;
		for(LectureDetailVO detail : lectureDetails){
			credit += detail.getCredit();
		}
		return credit;
	}
	
	private void addSectionRequirement(List<ResultVO> results) {
		if(sectionRequirement == null) return;
		ResultVO result = new ResultVO();
		result.setName("핵심 교양 이수 영역 수");
		result.setRequirement(sectionRequirement.getCutline());
		result.setScore(getCountBySection());
		results.add(result);
	}
	
	private int getCountBySection() {
		List<Integer> sectionCount = new ArrayList<>();
		for(LectureDetailVO detail : lectureDetails){
			if(!isSection(detail.getSectionId())) continue;
			if(sectionCount.contains(detail.getSectionId())) continue;
			sectionCount.add(detail.getSectionId());
		}
		return sectionCount.size();
	}
	
	private boolean isSection(int sectionId) {
		for(SectionVO section : sections){
			if(section.getId() == sectionId) return true;
		}
		return false;
	}
	private void addCategoryRequirement(List<ResultVO> results) {
		for(LectureCategoryRequirementVO requirement : categoryRequirements){
			ResultVO result = new ResultVO();
			result.setName(requirement.getLectureCategoryName());
			result.setRequirement(requirement.getCutline());
			result.setScore(getCreditByCategory(requirement.getLectureCategoryId()));
			results.add(result);	
		}
	}
	
	private void addTypeRequirement(List<ResultVO> results) {
		for(LectureTypeRequirementVO typeRequirement : typeRequirements){
			ResultVO result = new ResultVO();
			result.setName(typeRequirement.getLectureTypeName() +" " + typeRequirement.getLectureCategoryName());
			result.setRequirement(typeRequirement.getCutline());
			result.setScore(getCreditByType(typeRequirement.getLectureTypeId()));
			results.add(result);
		}
	}
	
	private int getCreditByType(int lectureTypeId) {
		int credit = 0;
		for(LectureDetailVO detail : lectureDetails){
			if(detail.getLectureTypeId() == lectureTypeId) 
				credit += detail.getCredit();
		}
		return credit;
	}
	private int getCreditByCategory(int categoryId) {
		int credit = 0;
		for(LectureDetailVO detail : lectureDetails){
			if(detail.getLectureCategoryId() == categoryId) 
				credit += detail.getCredit();
		}
		return credit;
	}
}
