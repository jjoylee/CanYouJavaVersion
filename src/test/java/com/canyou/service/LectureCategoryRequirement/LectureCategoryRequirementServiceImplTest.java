package com.canyou.service.LectureCategoryRequirement;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.canyou.model.LectureCategoryRequirement.LectureCategoryRequirementDao;
import com.canyou.model.LectureCategoryRequirement.LectureCategoryRequirementVO;
import com.canyou.service.LectureCategoryRequirement.LectureCategoryRequirementServiceImpl;

public class LectureCategoryRequirementServiceImplTest {
	
	private LectureCategoryRequirementServiceImpl service;
	private LectureCategoryRequirementDao dao;
	
	@Before
	public void setUp(){
		 service = new LectureCategoryRequirementServiceImpl();
		 dao = mock(LectureCategoryRequirementDao.class);
		 service.lectureCategoryRequirementDao = dao;
	}
	
	@Test
	public void insertTest() {
		LectureCategoryRequirementVO vo = mock(LectureCategoryRequirementVO.class);
		when(service.insert(vo)).thenReturn(1);
		int resultVal = service.insert(vo);
		verify(dao, times(1)).insert(vo);
		assertEquals(1, resultVal);
	}
    
	@Test
	public void updateTest() {
		LectureCategoryRequirementVO vo = mock(LectureCategoryRequirementVO.class);
		when(service.update(vo)).thenReturn(1);
		int resultVal = service.update(vo);
		verify(dao, times(1)).update(vo);
		assertEquals(1, resultVal);
	}
	
	@Test
	public void deleteTest() {
		int deleteId = 1;
		when(service.delete(deleteId)).thenReturn(2);
		int resultVal = service.delete(deleteId);
		verify(dao, times(1)).delete(deleteId);
		assertEquals(2, resultVal);
	}
	
	@Test
	public void findByAccountIdTest() {
		List expectList = mock(List.class);
		when(service.findByAccountId(1)).thenReturn((List<LectureCategoryRequirementVO>) expectList);		
		List resultList = service.findByAccountId(1);
		verify(dao, times(1)).findByAccountId(1);
		assertEquals(expectList, resultList);
	}
	
	@Test
	public void findByAccountAndCategoryIdTest() {
		LectureCategoryRequirementVO expectObj = mock(LectureCategoryRequirementVO.class);
		when(service.findByAccountAndCategoryId(1, 2)).thenReturn(expectObj);		
		LectureCategoryRequirementVO resultObj = service.findByAccountAndCategoryId(1,2);
		verify(dao, times(1)).findByAccountAndCategoryId(1,2);
		assertEquals(expectObj, resultObj);
	}
	
	@Test
	public void findByIdTest() {
		LectureCategoryRequirementVO expectObj = mock(LectureCategoryRequirementVO.class);
		when(service.findById(1)).thenReturn(expectObj);		
		LectureCategoryRequirementVO resultObj = service.findById(1);
		verify(dao, times(1)).findById(1);
		assertEquals(expectObj, resultObj);
	}
}