package com.canyou.service.LectureCategoryRequirement;

import java.util.List;

import com.canyou.model.LectureCategoryRequirement.LectureCategoryRequirementVO;

public interface LectureCategoryRequirementService {
	int insert(LectureCategoryRequirementVO vo);
    int update(LectureCategoryRequirementVO vo);
    int delete(int id);
    List<LectureCategoryRequirementVO> findByAccountId(int accountId);
    LectureCategoryRequirementVO findByAccountAndCategoryId(int accountId, int category);
    LectureCategoryRequirementVO findById(int id);
}
