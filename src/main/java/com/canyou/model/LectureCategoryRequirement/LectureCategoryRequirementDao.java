package com.canyou.model.LectureCategoryRequirement;

import java.util.List;

public interface LectureCategoryRequirementDao {
	int insert(LectureCategoryRequirementVO vo);
    int update(LectureCategoryRequirementVO vo);
    int delete(int id);
    List<LectureCategoryRequirementVO> findByAccountId(int accountId);
    LectureCategoryRequirementVO findByAccountAndCategoryId(int accountId, int category);
    LectureCategoryRequirementVO findById(int id);
}
