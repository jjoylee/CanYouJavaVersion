package com.canyou.model.SectionRequirement;

import java.util.List;

public interface SectionRequirementDao {
	int insert(SectionRequirementVO vo);
    int update(SectionRequirementVO vo);
    int delete(int id);
    List<SectionRequirementVO> findByAccountId(int accountId);
    SectionRequirementVO findById(int id);
    SectionRequirementVO findByAccountIdForCheck(int accountId);	
}
