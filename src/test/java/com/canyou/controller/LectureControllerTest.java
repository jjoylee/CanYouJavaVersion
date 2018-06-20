package com.canyou.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
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
	Map<String, String> expect;
	DataAccessException exception;
	List<LectureCategoryVO> categoryList;
	List<LectureTypeVO> typeList;
	
	
	@Before 
	public void setUp() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		MockGenerator.setMock(this);
		ctrl = new LectureController();
		setService();
		spy = spy(ctrl);
	}
	
	private void setService() {
		ctrl.lectureDetailService = detailService;
		ctrl.lectureCategoryService = categoryService;
		ctrl.lectureTypeService = typeService;
		ctrl.sectionService = sectionService;
	}
	
	private void setException(){
		exception = mock(DataAccessException.class);
		when(exception.getMessage()).thenReturn("데이터 에러");
		doReturn(expect).when(spy).getFailMessage("데이터 에러");
	}

	@Test
	public void list_test(){
		doReturn(1).when(spy).loginId(session);
		List<LectureDetailVO> list = mock(List.class);
		when(detailService.findByAccountId(any(Integer.class))).thenReturn(list);
		String result = spy.list(model, session);
		verify(detailService, times(1)).findByAccountId(1);
		verify(model,  times(1)).addAttribute("list",list);
		assertEquals("/lecture/list", result);
	}
	
	@Test
	public void registerGET_test(){
		LectureCategoryVO category = mock(LectureCategoryVO.class);
		LectureTypeVO type = mock(LectureTypeVO.class);
		doReturn(categoryList).when(spy).sendCategoryListToView(model);
		when(categoryList.get(0)).thenReturn(category);
		when(category.getId()).thenReturn(0);
		when(typeList.get(0)).thenReturn(type);
		when(type.getId()).thenReturn(0);
		doReturn(typeList).when(spy).sendTypeListToView(model, 0);
		doNothing().when(spy).sendSectionListToView(model, 0);
		String result = spy.register(model);
		assertEquals("/lecture/register", result);
	}
	
	@Test
	public void sendSectionListToView_test(){
		List<SectionVO> sectionList = mock(List.class);
		when(sectionService.findByTypeId(0)).thenReturn(sectionList);
		ctrl.sendSectionListToView(model,0);
		verify(model,times(1)).addAttribute("sectionList", sectionList);
	}
	
	@Test
	public void sendTypeListToView_test(){
		when(typeService.findByCategoryId(0)).thenReturn(typeList);
		List<LectureTypeVO> result = ctrl.sendTypeListToView(model,0);
		verify(model,times(1)).addAttribute("typeList", typeList);
		assertEquals(result, typeList);
	}
	
	@Test
	public void sendCategoryListToView_test(){
		when(categoryService.findAll()).thenReturn(categoryList);
		List<LectureCategoryVO> result = ctrl.sendCategoryListToView(model);
		verify(model,times(1)).addAttribute("categoryList",categoryList);
		assertEquals(result, categoryList);
	}
	
	@Test
	public void register_already_exist_lecture_test(){
		when(lectureDetail.getName()).thenReturn("title");
		doReturn(true).when(spy).existLectureDetail("title", session);
		doReturn(expect).when(spy).getFailMessage("이미 존재합니다.");
		Map<String, String> result = spy.register(lectureDetail, session);
		verify(spy, times(1)).getFailMessage("이미 존재합니다.");
		assertEquals(expect, result);
	}
	
	@Test
	public void register_success_test(){
		when(lectureDetail.getName()).thenReturn("title");
		doReturn(false).when(spy).existLectureDetail("title", session);
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
		doReturn(1).when(spy).loginId(session);
		setException();
		when(detailService.insert(lectureDetail)).thenThrow(exception);
		Map<String, String> result = spy.register(lectureDetail, session);
		verify(lectureDetail, times(1)).setAccountId(1);
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
		doReturn(1).when(spy).loginId(session);
		when(detailService.findByAccountAndTitle(any(Integer.class), any(String.class))).thenReturn(lectureDetail);
		assertTrue(spy.existLectureDetail("title", session));
	}
	
	@Test 
	public void lectureDetail_not_exist_test(){
		doReturn(1).when(spy).loginId(session);
		when(detailService.findByAccountAndTitle(any(Integer.class), any(String.class))).thenReturn(null);
		assertFalse(spy.existLectureDetail("title", session));
	}
	
	@Test
	public void delete_lecture_success_test(){
		doReturn(expect).when(spy).getSuccessMessage();
		Map<String, String> result = spy.delete(1);
		verify(detailService,times(1)).delete(1);
		verify(spy,times(1)).getSuccessMessage();
		assertEquals(expect, result);
	}
	
	@Test
	public void delete_lecture_exception_test(){
		setException();
		when(detailService.delete(1)).thenThrow(exception);
		Map<String, String> result = spy.delete(1);
		verify(spy,times(1)).getFailMessage("데이터 에러");
		assertEquals(expect, result);
	}
	
	@Test
	public void update_lecture_get_test(){
		when(detailService.findById(any(Integer.class))).thenReturn(lectureDetail);
		doReturn(categoryList).when(spy).sendCategoryListToView(model);
		when(lectureDetail.getLectureCategoryId()).thenReturn(1);
		when(lectureDetail.getLectureTypeId()).thenReturn(2);
		doReturn(typeList).when(spy).sendTypeListToView(model,1);
		doNothing().when(spy).sendSectionListToView(model,2);
		String result = spy.update(1, model);
		verify(detailService,times(1)).findById(any(Integer.class));
		verify(model,times(1)).addAttribute("lectureDetail", lectureDetail);
		verify(spy,times(1)).sendCategoryListToView(model);
		verify(spy,times(1)).sendTypeListToView(model, 1);
		verify(spy,times(1)).sendSectionListToView(model, 2);
		assertEquals("/lecture/update", result);
	}
}
