package com.canyou.service.LectureDetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.canyou.model.LectureDetail.LectureDetailDao;
import com.canyou.model.LectureDetail.LectureDetailVO;

@Service
public class LectureDetailServiceImpl implements LectureDetailService {
	@Autowired
	LectureDetailDao lectureDetailDao;
	
	@Override
	public int insert(LectureDetailVO vo) {
		return lectureDetailDao.insert(vo);
	}

	@Override
	public int update(LectureDetailVO vo) {
		return lectureDetailDao.update(vo);
	}

	@Override
	public int delete(int id) {
		return lectureDetailDao.delete(id);
	}

	@Override
	public List<LectureDetailVO> findByAccountId(int accountId) {
		return lectureDetailDao.findByAccountId(accountId);
	}

	@Override
	public List<LectureDetailVO> findAll() {
		return lectureDetailDao.findAll();
	}

	@Override
	public LectureDetailVO findById(int id) {
		return lectureDetailDao.findById(id);
	}

	@Override
	public LectureDetailVO findByAccountAndTitle(int accountId, String title) {
		return lectureDetailDao.findByAccountAndTitle(accountId, title);
	}

}
