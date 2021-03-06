package com.canyou.service.LectureCategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canyou.model.LectureCategory.LectureCategoryDao;
import com.canyou.model.LectureCategory.LectureCategoryVO;

@Service
public class LectureCategoryServiceImpl implements LectureCategoryService{
	
	@Autowired 
	public LectureCategoryDao lectureCategoryDao;
	
	@Override
	public List<LectureCategoryVO> findAll() {
		return lectureCategoryDao.findAll();
	}

	@Override
	public List<LectureCategoryVO> findLectureTypeExist() {
		return lectureCategoryDao.findLectureTypeExist();
	}

}
