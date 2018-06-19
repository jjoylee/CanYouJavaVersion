package com.canyou.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.spy;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;

import com.canyou.model.Account.AccountVO;
import com.canyou.model.LectureCategory.LectureCategoryVO;
import com.canyou.model.LectureDetail.LectureDetailVO;
import com.canyou.model.LectureType.LectureTypeVO;
import com.canyou.model.Section.SectionVO;
import com.canyou.service.LectureCategory.LectureCategoryService;
import com.canyou.service.LectureDetail.LectureDetailService;
import com.canyou.service.LectureType.LectureTypeService;
import com.canyou.service.Section.SectionService;


public class LectureControllerTest extends AbstractTest{
	
	LectureController ctrl;
	LectureController spy;
	LectureDetailService detailService;
	Model model;
	LectureCategoryService categoryService;
	LectureTypeService typeService;
	SectionService sectionService;
	HttpSession session;
	LectureDetailVO lectureDetail;
	AccountVO account;
	
	@Before 
	public void setUp() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		ctrl = new LectureController();
		MockGenerator.setMock(this);
		setService();
		spy = spy(ctrl);
		lectureDetail = mock(LectureDetailVO.class);
		account = mock(AccountVO.class);
	}
	
	private void setService() {
		ctrl.lectureDetailService = detailService;
		ctrl.lectureCategoryService = categoryService;
		ctrl.lectureTypeService = typeService;
		ctrl.sectionService = sectionService;
	}

	@Test
	public void list_test(){
		System.out.println(detailService.toString());
		doReturn(account).when(spy).loginAccount(session);
		when(account.getId()).thenReturn(1);
		List<LectureDetailVO> list = mock(List.class);
		when(detailService.findByAccountId(any(Integer.class))).thenReturn(list);
		String result = spy.list(model, session);
		verify(detailService, times(1)).findByAccountId(1);
		verify(model,  times(1)).addAttribute("list",list);
		assertEquals("/lecture/list", result);
	}
	
	@Test
	public void registerGET_test(){
		List<LectureCategoryVO> categoryList = mock(List.class);
		List<LectureTypeVO> typeList = mock(List.class);
		List<SectionVO> sectionList = mock(List.class);
		LectureCategoryVO category = mock(LectureCategoryVO.class);
		LectureTypeVO type = mock(LectureTypeVO.class);
		when(categoryService.findAll()).thenReturn(categoryList);
		when(categoryList.get(0)).thenReturn(category);
		when(category.getId()).thenReturn(1);
		when(typeService.findByCategoryId(1)).thenReturn(typeList);
		when(typeList.get(0)).thenReturn(type);
		when(type.getId()).thenReturn(1);
		when(sectionService.findByTypeId(1)).thenReturn(sectionList);
		String result = ctrl.register(model);
		verify(model,times(1)).addAttribute("categoryList",categoryList);
		verify(model,times(1)).addAttribute("typeList",typeList);
		verify(model,times(1)).addAttribute("sectionList", sectionList);
		assertEquals("/lecture/register", result);
	}
	
	@Test
	public void register_already_exist_lecture_test(){
		when(lectureDetail.getName()).thenReturn("title");
		doReturn(true).when(spy).existLectureDetail("title", session);
		Map<String, String> expect = mock(Map.class);
		doReturn(expect).when(spy).getFailMessage("이미 존재합니다.");
		Map<String, String> result = spy.register(lectureDetail, session);
		verify(spy, times(1)).getFailMessage("이미 존재합니다.");
		assertEquals(expect, result);
	}
	
	@Test
	public void register_success_test(){
		when(lectureDetail.getName()).thenReturn("title");
		doReturn(false).when(spy).existLectureDetail("title", session);
		Map<String, String> expect = mock(Map.class);
		doReturn(expect).when(spy).getSuccessMessage();
		when(spy.loginAccount(session)).thenReturn(account);
		Map<String, String> result = spy.register(lectureDetail, session);
		verify(detailService,times(1)).insert(lectureDetail);
		verify(spy, times(1)).getSuccessMessage();
		assertEquals(expect, result);
	}
	
	@Test
	public void register_exception_test(){
		when(lectureDetail.getName()).thenReturn("title");
		doReturn(false).when(spy).existLectureDetail("title", session);
		DataAccessException exception = mock(DataAccessException.class);
		when(exception.getMessage()).thenReturn("데이터 에러");
		when(detailService.insert(lectureDetail)).thenThrow(exception);
		Map<String, String> expect = mock(Map.class);
		doReturn(expect).when(spy).getFailMessage("데이터 에러");
		Map<String, String> result = spy.register(lectureDetail, session);
		verify(spy, times(1)).getFailMessage("데이터 에러");
		assertEquals(expect, result);
	}
	
	@Test
	public void typePartial_test(){
		List<LectureTypeVO> typeList = mock(List.class);
		when(typeService.findByCategoryId(any(Integer.class))).thenReturn(typeList);
		String result = ctrl.typePartial(model, 1);
		verify(typeService, times(1)).findByCategoryId(any(Integer.class));
		verify(model, times(1)).addAttribute("typeList",typeList);
		assertEquals("/lecture/typePartial",result);
	}
	
	@Test
	public void sectionPartial_test(){
		List<SectionVO> sectionList = mock(List.class);
		when(sectionService.findByTypeId(any(Integer.class))).thenReturn(sectionList);
		String result = ctrl.sectionPartial(model, 1);
		verify(sectionService, times(1)).findByTypeId(any(Integer.class));
		verify(model, times(1)).addAttribute("sectionList",sectionList);
		assertEquals("/lecture/sectionPartial",result);
	}
	
	@Test 
	public void lectureDetail_already_exist_test(){
		doReturn(account).when(spy).loginAccount(session);
		when(account.getId()).thenReturn(1);
		when(detailService.findByAccountAndTitle(any(Integer.class), any(String.class))).thenReturn(lectureDetail);
		assertTrue(spy.existLectureDetail("title", session));
	}
	
	@Test 
	public void lectureDetail_not_exist_test(){
		doReturn(account).when(spy).loginAccount(session);
		when(account.getId()).thenReturn(1);
		when(detailService.findByAccountAndTitle(any(Integer.class), any(String.class))).thenReturn(null);
		assertFalse(spy.existLectureDetail("title", session));
	}
}
