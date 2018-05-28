package com.canyou.service.LectureTypeRequirement;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.canyou.model.LectureTypeRequirement.LectureTypeRequirementDao;
import com.canyou.model.LectureTypeRequirement.LectureTypeRequirementVO;

public class LectureTypeRequirementServiceImplTest {

	LectureTypeRequirementServiceImpl service;
	LectureTypeRequirementDao dao;
	
	@Before
	public void setUp(){
		service = new LectureTypeRequirementServiceImpl();
		dao = mock(LectureTypeRequirementDao.class);
		service.lectureTypeRequirementDao = dao;
	}
	
	@Test
	public void insertTest() {
		LectureTypeRequirementVO vo = mock(LectureTypeRequirementVO.class);
		when(service.insert(vo)).thenReturn(1);
		int resultVal = service.insert(vo);
		verify(dao, times(1)).insert(vo);
		assertEquals(1,resultVal);
	}

	@Test
	public void updateTest() {
		LectureTypeRequirementVO vo = mock(LectureTypeRequirementVO.class);
		when(service.update(vo)).thenReturn(1);
		int resultVal = service.update(vo);
		verify(dao, times(1)).update(vo);
		assertEquals(1,resultVal);
	}
	
	@Test
	public void deleteTest() {
		when(service.delete(1)).thenReturn(2);
		int resultVal = service.delete(1);
		verify(dao, times(1)).delete(1);
		assertEquals(2,resultVal);
	}
	
	@Test
	public void findByAccountIdTest() {
		List<LectureTypeRequirementVO> expectList = mock(List.class);
		when(service.findByAccountId(1)).thenReturn(expectList);
		List<LectureTypeRequirementVO> resultList = service.findByAccountId(1);
		verify(dao, times(1)).findByAccountId(1);
		assertEquals(expectList, resultList);
	}
	
	@Test
	public void findByIdTest() {
		LectureTypeRequirementVO expectObj = mock(LectureTypeRequirementVO.class);
		when(service.findById(1)).thenReturn(expectObj);
		LectureTypeRequirementVO resultObj = service.findById(1);
		verify(dao, times(1)).findById(1);
		assertEquals(expectObj, resultObj);
	}
	
	@Test
	public void findByAccountAndTypeIdTest() {
		LectureTypeRequirementVO expectObj = mock(LectureTypeRequirementVO.class);
		when(service.findByAccountAndTypeId(1, 2)).thenReturn(expectObj);
		LectureTypeRequirementVO resultObj = service.findByAccountAndTypeId(1, 2);
		verify(dao, times(1)).findByAccountAndTypeId(1, 2);
		assertEquals(expectObj, resultObj);
	}
}
