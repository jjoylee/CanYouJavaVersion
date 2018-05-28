package com.canyou.service.SectionRequirement;

import java.util.List;

import com.canyou.model.SectionRequirement.SectionRequirementVO;

public interface SectionRequirementService {
	int insert(SectionRequirementVO vo);
    int update(SectionRequirementVO vo);
    int delete(int id);
    List<SectionRequirementVO> findByAccountId(int accountId);
    SectionRequirementVO findById(int id);
    SectionRequirementVO findByAccountIdForCheck(int accountId);
}
