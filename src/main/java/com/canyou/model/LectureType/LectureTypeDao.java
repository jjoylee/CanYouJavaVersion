package com.canyou.model.LectureType;

import java.util.List;

public interface LectureTypeDao {
    List<LectureTypeVO> findByCategoryId(int id);
}
