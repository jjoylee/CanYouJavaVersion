package com.canyou.service.ScoreRequirement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canyou.model.ScoreRequirement.ScoreRequirementDao;
import com.canyou.model.ScoreRequirement.ScoreRequirementVO;

@Service
public class ScoreRequirementServiceImpl implements ScoreRequirementService{

	@Autowired
	ScoreRequirementDao scoreRequirementDao;
	
	@Override
	public int insert(ScoreRequirementVO vo) {
		return scoreRequirementDao.insert(vo);
	}

	@Override
	public int update(ScoreRequirementVO vo) {
		return scoreRequirementDao.update(vo);
	}

	@Override
	public int delete(int id) {
		return scoreRequirementDao.delete(id);
	}

	@Override
	public List<ScoreRequirementVO> findByAccountId(int accountId) {
		return scoreRequirementDao.findByAccountId(accountId);
	}

	@Override
	public ScoreRequirementVO findById(int id) {
		return scoreRequirementDao.findById(id);
	}

	@Override
	public ScoreRequirementVO findByAccountIdForCheck(int accountId) {
		return scoreRequirementDao.findByAccountIdForCheck(accountId);
	}

}
