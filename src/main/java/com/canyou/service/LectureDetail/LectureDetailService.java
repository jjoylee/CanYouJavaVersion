package com.canyou.service.LectureDetail;

import java.util.List;

import com.canyou.model.LectureDetail.LectureDetailVO;

public interface LectureDetailService {
	int insert(LectureDetailVO vo);
    int update(LectureDetailVO vo);
    int delete(int id);
    List<LectureDetailVO> findByAccountId(int accountId);
    List<LectureDetailVO> findAll();
    LectureDetailVO findById(int id);
    LectureDetailVO findByAccountAndTitle(int accountId, String title);
}
