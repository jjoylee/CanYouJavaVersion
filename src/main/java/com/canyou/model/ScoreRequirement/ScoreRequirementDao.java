package com.canyou.model.ScoreRequirement;

import java.util.List;

public interface ScoreRequirementDao {
	int insert(ScoreRequirementVO vo);
    int update(ScoreRequirementVO vo);
    int delete(int id);
    List<ScoreRequirementVO> findByAccountId(int accountId);
    ScoreRequirementVO findById(int id);
    ScoreRequirementVO findByAccountIdForCheck(int accountId);
}
