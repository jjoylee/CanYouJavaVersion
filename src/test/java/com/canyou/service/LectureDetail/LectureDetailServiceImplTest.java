package com.canyou.service.LectureDetail;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.canyou.model.LectureDetail.LectureDetailDao;
import com.canyou.model.LectureDetail.LectureDetailVO;

public class LectureDetailServiceImplTest {
	
	private LectureDetailServiceImpl service;
	private LectureDetailDao dao;
	
	@Before
	public void setUp(){
		service = new LectureDetailServiceImpl();
		dao = mock(LectureDetailDao.class);
		service.lectureDetailDao = dao;
	}
	
	@Test
	public void insertTest() {
		LectureDetailVO vo = mock(LectureDetailVO.class);
		when(service.insert(vo)).thenReturn(1);
		int resultVal = service.insert(vo);
		verify(dao, times(1)).insert(vo);
		assertEquals(1, resultVal);
	}
	
	@Test
	public void updateTest() {
		LectureDetailVO vo = mock(LectureDetailVO.class);
		when(service.update(vo)).thenReturn(1);
		int resultVal = service.update(vo);
		verify(dao, times(1)).update(vo);
		assertEquals(1, resultVal);
	}
	
	@Test
	public void deleteTest() {
		when(service.delete(1)).thenReturn(2);
		int resultVal = service.delete(1);
		verify(dao, times(1)).delete(1);
		assertEquals(2, resultVal);
	}
	
	@Test
	public void findByAccountIdTest() {
		List expectList = mock(List.class);
		when(service.findByAccountId(1)).thenReturn((List<LectureDetailVO>)expectList);
		List<LectureDetailVO> resultList = service.findByAccountId(1);
		verify(dao, times(1)).findByAccountId(1);
		assertEquals(expectList, resultList);
	}
	
	@Test
	public void findAllTest() {
		List expectList = mock(List.class);
		when(service.findAll()).thenReturn((List<LectureDetailVO>)expectList);
		List<LectureDetailVO> resultList = service.findAll();
		verify(dao, times(1)).findAll();
		assertEquals(expectList, resultList);
	}
	
	@Test
	public void findByIdTest() {
		LectureDetailVO expectObj = mock(LectureDetailVO.class);
		when(service.findById(1)).thenReturn(expectObj);
		LectureDetailVO resultObj = service.findById(1);
		verify(dao, times(1)).findById(1);
		assertEquals(expectObj, resultObj);
	}
	
	@Test
	public void findByAccountAndTitleTest() {
		LectureDetailVO expectObj = mock(LectureDetailVO.class);
		when(service.findByAccountAndTitle(1, "title")).thenReturn(expectObj);
		LectureDetailVO resultObj = service.findByAccountAndTitle(1,"title");
		verify(dao, times(1)).findByAccountAndTitle(1,"title");
		assertEquals(expectObj, resultObj);
	}
}
