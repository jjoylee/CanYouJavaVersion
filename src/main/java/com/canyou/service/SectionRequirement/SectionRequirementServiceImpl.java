package com.canyou.service.SectionRequirement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canyou.model.SectionRequirement.SectionRequirementDao;
import com.canyou.model.SectionRequirement.SectionRequirementVO;

@Service
public class SectionRequirementServiceImpl implements SectionRequirementService{

	@Autowired
	SectionRequirementDao sectionRequirementDao;
	
	@Override
	public int insert(SectionRequirementVO vo) {
		return sectionRequirementDao.insert(vo);
	}

	@Override
	public int update(SectionRequirementVO vo) {
		return sectionRequirementDao.update(vo);
	}

	@Override
	public int delete(int id) {
		return sectionRequirementDao.delete(id);
	}

	@Override
	public List<SectionRequirementVO> findByAccountId(int accountId) {
		return sectionRequirementDao.findByAccountId(accountId);
	}

	@Override
	public SectionRequirementVO findById(int id) {
		return sectionRequirementDao.findById(id);
	}

	@Override
	public SectionRequirementVO findByAccountIdForCheck(int accountId) {
		return sectionRequirementDao.findByAccountIdForCheck(accountId);
	}

}
