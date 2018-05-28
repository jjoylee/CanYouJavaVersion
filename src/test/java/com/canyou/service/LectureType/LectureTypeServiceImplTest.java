package com.canyou.service.LectureType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

import com.canyou.model.LectureType.LectureTypeDao;
import com.canyou.model.LectureType.LectureTypeVO;

public class LectureTypeServiceImplTest {

	@Test
	public void findByCategoryIdTest() {
		LectureTypeServiceImpl service = new LectureTypeServiceImpl();
		LectureTypeDao dao = mock(LectureTypeDao.class);
		service.lectureTypeDao = dao;
		List<LectureTypeVO> expectList = mock(List.class);
		when(service.findByCategoryId(1)).thenReturn(expectList);
		List<LectureTypeVO> resultList = service.findByCategoryId(1);
		verify(dao, times(1)).findByCategoryId(1);
		assertEquals(expectList, resultList);
	}

}
