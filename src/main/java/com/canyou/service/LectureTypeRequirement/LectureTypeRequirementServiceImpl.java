package com.canyou.service.LectureTypeRequirement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canyou.model.LectureTypeRequirement.LectureTypeRequirementDao;
import com.canyou.model.LectureTypeRequirement.LectureTypeRequirementVO;

@Service
public class LectureTypeRequirementServiceImpl implements LectureTypeRequirementService{

	@Autowired
	LectureTypeRequirementDao lectureTypeRequirementDao;
	
	@Override
	public int insert(LectureTypeRequirementVO vo) {
		return lectureTypeRequirementDao.insert(vo);
	}

	@Override
	public int update(LectureTypeRequirementVO vo) {
		return lectureTypeRequirementDao.update(vo);
	}

	@Override
	public int delete(int id) {
		return lectureTypeRequirementDao.delete(id);
	}

	@Override
	public List<LectureTypeRequirementVO> findByAccountId(int accountId) {
		return lectureTypeRequirementDao.findByAccountId(accountId);
	}

	@Override
	public LectureTypeRequirementVO findById(int id) {
		return lectureTypeRequirementDao.findById(id);
	}

	@Override
	public LectureTypeRequirementVO findByAccountAndTypeId(int accountId, int type) {
		return lectureTypeRequirementDao.findByAccountAndTypeId(accountId, type);
	}

}
