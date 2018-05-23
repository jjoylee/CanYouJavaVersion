package com.canyou.model.LectureTypeRequirement;

import java.util.List;

public interface LectureTypeRequirementDao {
	int insert(LectureTypeRequirementVO vo);
    int update(LectureTypeRequirementVO vo);
    int delete(int id);
    List<LectureTypeRequirementVO> findByAccountId(int accountId);
    LectureTypeRequirementVO findById(int id);
    LectureTypeRequirementVO findByAccountAndTypeId(int accountId, int type);
}
