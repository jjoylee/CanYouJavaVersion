package com.canyou.model.LectureCategory;

import java.util.List;

public interface LectureCategoryDao {
	int insert(LectureCategoryVO item);
    int update(LectureCategoryVO item);
    int delete(int id);
    List<LectureCategoryVO> findAll();
    List<LectureCategoryVO> findLectureTypeExist();
}
