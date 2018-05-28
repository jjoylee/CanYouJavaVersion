package com.canyou.service.SectionRequirement;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.canyou.model.SectionRequirement.SectionRequirementDao;
import com.canyou.model.SectionRequirement.SectionRequirementVO;

public class SectionRequirementServiceImplTest {

	SectionRequirementServiceImpl service;
	SectionRequirementDao dao;
	
	@Before
	public void setUp(){
		service = new SectionRequirementServiceImpl();
		dao = mock(SectionRequirementDao.class);
		service.sectionRequirementDao = dao;
	}
	
	@Test
	public void insertTest() {
		SectionRequirementVO vo = mock(SectionRequirementVO.class);
		when(service.insert(vo)).thenReturn(1);
		int resultVal = service.insert(vo);
		verify(dao, times(1)).insert(vo);
		assertEquals(1, resultVal);
	}
	
	@Test
	public void updateTest() {
		SectionRequirementVO vo = mock(SectionRequirementVO.class);
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
		List<SectionRequirementVO> expectList = mock(List.class);
		when(service.findByAccountId(1)).thenReturn(expectList);
		List<SectionRequirementVO> resultList = service.findByAccountId(1);
		verify(dao, times(1)).findByAccountId(1);
		assertEquals(expectList, resultList);
	}
	
	@Test
	public void findByIdTest() {
		SectionRequirementVO expectObj = mock(SectionRequirementVO.class);
		when(service.findById(1)).thenReturn(expectObj);
		SectionRequirementVO resultObj = service.findById(1);
		verify(dao, times(1)).findById(1);
		assertEquals(expectObj, resultObj);
	}
	
	@Test
	public void findByAccountIdForCheckTest() {
		SectionRequirementVO expectObj = mock(SectionRequirementVO.class);
		when(service.findByAccountIdForCheck(1)).thenReturn(expectObj);
		SectionRequirementVO resultObj = service.findByAccountIdForCheck(1);
		verify(dao, times(1)).findByAccountIdForCheck(1);
		assertEquals(expectObj, resultObj);
	}
}
