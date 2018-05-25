package com.canyou.service.LectureCategory;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.canyou.model.LectureCategory.LectureCategoryDao;
import com.canyou.model.LectureCategory.LectureCategoryVO;

public class LectureCategoryServiceImplTest {
	LectureCategoryServiceImpl service;
	private LectureCategoryDao dao;
	
	@Before
	public void setUp(){
		service = new LectureCategoryServiceImpl();
		dao = mock(LectureCategoryDao.class);
		service.lectureCategoryDao = dao;
	}
	
	@Test
	public void findAllTest() {
		List expectList = mock(List.class);
		when(service.findAll()).thenReturn((List<LectureCategoryVO>)expectList);
		List<LectureCategoryVO> resultList = service.findAll();
		verify(dao, times(1)).findAll();
		assertEquals(expectList, resultList);
	}
	
	@Test
	public void findLectureTypeExistTest(){
		List expectList = mock(List.class);
		when(service.findLectureTypeExist()).thenReturn((List<LectureCategoryVO>)expectList);
		List<LectureCategoryVO> resultList = service.findLectureTypeExist();
		verify(dao, times(1)).findLectureTypeExist();
		assertEquals(expectList, resultList);
	}
}
