package com.canyou.model.LectureDetail;

import java.util.List;

public interface LectureDetailDao {
	int insert(LectureDetailVO vo);
    int update(LectureDetailVO vo);
    int delete(int id);
    List<LectureDetailVO> findByAccountId(int accountId);
    List<LectureDetailVO> findAll();
    LectureDetailVO findById(int id);
    LectureDetailVO findByAccountAndTitle(int accountId, String title);
}
