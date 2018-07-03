package com.canyou.service.Result;

import java.util.List;

import com.canyou.model.LectureCategoryRequirement.LectureCategoryRequirementVO;
import com.canyou.model.LectureDetail.LectureDetailVO;
import com.canyou.model.LectureTypeRequirement.LectureTypeRequirementVO;
import com.canyou.model.Result.ResultVO;
import com.canyou.model.ScoreRequirement.ScoreRequirementVO;
import com.canyou.model.Section.SectionVO;
import com.canyou.model.SectionRequirement.SectionRequirementVO;

public interface ResultService{
	//Getter And Setter
	List<LectureCategoryRequirementVO> getCategoryRequirements();
	void setCategoryRequirements(List<LectureCategoryRequirementVO> categoryRequirements);
	List<LectureTypeRequirementVO> getTypeRequirements();
	void setTypeRequirements(List<LectureTypeRequirementVO> typeRequirements);
	ScoreRequirementVO getScoreRequirement();
	void setScoreRequirement(ScoreRequirementVO scoreRequirement);
	SectionRequirementVO getSectionRequirement();
	void setSectionRequirement(SectionRequirementVO sectionRequirement);
	List<SectionVO> getSections();
	void setSections(List<SectionVO> sections);
	List<LectureDetailVO> getLectureDetails();
	void setLectureDetails(List<LectureDetailVO> lectureDetails);
	
	//add Result
	List<ResultVO> getResults();
}
