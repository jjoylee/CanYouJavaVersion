package com.canyou.service.Section;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.canyou.model.Section.SectionDao;
import com.canyou.model.Section.SectionVO;

public class SectionServiceImplTest {

	SectionServiceImpl service;
	SectionDao dao;
	
	@Before
	public void setUp(){
		service = new SectionServiceImpl();
		dao = mock(SectionDao.class);
		service.sectionDao = dao;
	}
	
	@Test
	public void findAllTest() {
		List<SectionVO> expectList = mock(List.class);
		when(service.findAll()).thenReturn(expectList);
		List<SectionVO> resultList = service.findAll();
		verify(dao, times(1)).findAll();
		assertEquals(expectList, resultList);
	}

	@Test
	public void findByTypeIdTest() {
		List<SectionVO> expectList = mock(List.class);
		when(service.findByTypeId(1)).thenReturn(expectList);
		List<SectionVO> resultList = service.findByTypeId(1);
		verify(dao, times(1)).findByTypeId(1);
		assertEquals(expectList, resultList);
	}
}
