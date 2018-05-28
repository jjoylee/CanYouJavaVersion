package com.canyou.service.LectureTypeRequirement;

import java.util.List;

import com.canyou.model.LectureTypeRequirement.LectureTypeRequirementVO;

public interface LectureTypeRequirementService {
	int insert(LectureTypeRequirementVO vo);
    int update(LectureTypeRequirementVO vo);
    int delete(int id);
    List<LectureTypeRequirementVO> findByAccountId(int accountId);
    LectureTypeRequirementVO findById(int id);
    LectureTypeRequirementVO findByAccountAndTypeId(int accountId, int type);
}
