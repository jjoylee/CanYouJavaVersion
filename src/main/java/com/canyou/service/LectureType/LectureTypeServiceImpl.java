package com.canyou.service.LectureType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.canyou.model.LectureType.LectureTypeDao;
import com.canyou.model.LectureType.LectureTypeVO;

public class LectureTypeServiceImpl implements LectureTypeService{

	@Autowired
	LectureTypeDao lectureTypeDao;
	
	@Override
	public List<LectureTypeVO> findByCategoryId(int id) {
		return lectureTypeDao.findByCategoryId(id);
	}

}
