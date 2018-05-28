package com.canyou.service.LectureType;

import java.util.List;

import com.canyou.model.LectureType.LectureTypeVO;

public interface LectureTypeService {
	List<LectureTypeVO> findByCategoryId(int id);
}
