package com.canyou.service.LectureCategory;

import java.util.List;

import com.canyou.model.LectureCategory.LectureCategoryVO;

public interface LectureCategoryService {
	List<LectureCategoryVO> findAll();
    List<LectureCategoryVO> findLectureTypeExist();
}
