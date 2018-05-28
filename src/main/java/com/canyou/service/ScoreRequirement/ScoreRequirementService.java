package com.canyou.service.ScoreRequirement;

import java.util.List;

import com.canyou.model.ScoreRequirement.ScoreRequirementVO;

public interface ScoreRequirementService {
	int insert(ScoreRequirementVO vo);
    int update(ScoreRequirementVO vo);
    int delete(int id);
    List<ScoreRequirementVO> findByAccountId(int accountId);
    ScoreRequirementVO findById(int id);
    ScoreRequirementVO findByAccountIdForCheck(int accountId);
}
