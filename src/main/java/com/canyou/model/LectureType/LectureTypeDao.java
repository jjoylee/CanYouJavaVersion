package com.canyou.model.LectureType;

import java.util.List;

public interface LectureTypeDao {
	int insert(LectureTypeVO vo);
    int update(LectureTypeVO vo);
    int delete(int id);
    List<LectureTypeVO> findByCategoryId(int id);
    List<LectureTypeVO> findAll();
}
