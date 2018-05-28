package com.canyou.service.Section;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.canyou.model.Section.SectionDao;
import com.canyou.model.Section.SectionVO;

public class SectionServiceImpl implements SectionService{

	@Autowired 
	SectionDao sectionDao;
	
	@Override
	public List<SectionVO> findAll() {
		return sectionDao.findAll();
	}

	@Override
	public List<SectionVO> findByTypeId(int typeId) {
		return sectionDao.findByTypeId(typeId);
	}

}
