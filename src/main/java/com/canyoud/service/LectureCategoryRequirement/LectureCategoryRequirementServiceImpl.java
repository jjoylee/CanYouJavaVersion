package com.canyoud.service.LectureCategoryRequirement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.canyou.model.LectureCategoryRequirement.LectureCategoryRequirementDao;
import com.canyou.model.LectureCategoryRequirement.LectureCategoryRequirementVO;

public class LectureCategoryRequirementServiceImpl implements LectureCategoryRequirementService {
	
	@Autowired
	LectureCategoryRequirementDao lectureCategoryRequirementDao;
	
	@Override
	public int insert(LectureCategoryRequirementVO vo) {
		return lectureCategoryRequirementDao.insert(vo);
	}

	@Override
	public int update(LectureCategoryRequirementVO vo) {
		return lectureCategoryRequirementDao.update(vo);
	}

	@Override
	public int delete(int id) {
		return lectureCategoryRequirementDao.delete(id);
	}

	@Override
	public List<LectureCategoryRequirementVO> findByAccountId(int accountId) {
		return lectureCategoryRequirementDao.findByAccountId(accountId);
	}

	@Override
	public LectureCategoryRequirementVO findByAccountAndCategoryId(int accountId, int category) {
		return lectureCategoryRequirementDao.findByAccountAndCategoryId(accountId, category);
	}

	@Override
	public LectureCategoryRequirementVO findById(int id) {
		return lectureCategoryRequirementDao.findById(id);
	}

}
