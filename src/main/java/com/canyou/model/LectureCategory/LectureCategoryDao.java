package com.canyou.model.LectureCategory;

import java.util.List;

public interface LectureCategoryDao {
    List<LectureCategoryVO> findAll();
    List<LectureCategoryVO> findLectureTypeExist();
}
