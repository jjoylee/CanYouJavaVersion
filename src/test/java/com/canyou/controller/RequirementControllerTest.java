package com.canyou.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;

import com.canyou.model.LectureCategory.LectureCategoryVO;
import com.canyou.model.LectureCategoryRequirement.LectureCategoryRequirementVO;
import com.canyou.service.LectureCategory.LectureCategoryService;
import com.canyou.service.LectureCategoryRequirement.LectureCategoryRequirementService;

public class RequirementControllerTest {
	
	RequirementController ctrl;
	RequirementController spy;
	Model model;
	HttpSession session;
	LectureCategoryRequirementService categoryRequirementService;
	LectureCategoryService categoryService;
	LectureCategoryRequirementVO categoryRequirement;
	Map<String, String> expect;
	DataAccessException exception;
	List<LectureCategoryVO> categoryList;
	
	@Before
	public void setUp() throws IllegalArgumentException, IllegalAccessException{
		MockGenerator.setMock(this);
		ctrl = new RequirementController();
		ctrl.categoryRequirementService = categoryRequirementService;
		ctrl.categoryService = categoryService;
		spy = spy(ctrl);
	}
	
	@Test
	public void category_test() {
		List<LectureCategoryRequirementVO> list = mock(List.class);
		loginIdWhen();
		when(categoryRequirementService.findByAccountId(1)).thenReturn(list);
		String result = spy.category(model, session);
		verify(categoryRequirementService, times(1)).findByAccountId(1);
		loginIdVerify();
		verify(model, times(1)).addAttribute("list", list);
		assertEquals("/requirement/category", result);
	}

	private void loginIdVerify() {
		verify(spy,times(1)).loginId(session);
	}

	private void loginIdWhen() {
		doReturn(1).when(spy).loginId(session);
	}
	
	@Test
	public void categoryRegister_get_test() {
		when(categoryService.findAll()).thenReturn(categoryList);
		String result = ctrl.categoryRegister(model);
		verify(categoryService, times(1)).findAll();
		verify(model,times(1)).addAttribute("list",categoryList);
		assertEquals("/requirement/categoryRegister", result);
	}
	
	@Test
	public void categoryRegister_post_already_exist_test() {
		existCategoryRequirementWhen(true);	
		failMessageWhen("이미 존재합니다.");
		Map<String, String> result = spy.categoryRegister(categoryRequirement, session);
		existCategoryRequirementVerify(result);
		failMessageVerify("이미 존재합니다.");
	}

	private void failMessageVerify(String message) {
		verify(spy,times(1)).getFailMessage(message);
	}

	private void failMessageWhen(String message) {
		doReturn(expect).when(spy).getFailMessage(message);
	}

	private void existCategoryRequirementVerify(Map result) {
		verify(categoryRequirement, times(1)).getLectureCategoryId();
		verify(spy,times(1)).existCategoryRequirement(1, session);
		assertEquals(expect, result);
	}
	
	@Test
	public void categoryRegister_post_success_test() {
		categoryReqirementInsertWhen();
		successMessageWhen();
		Map<String, String> result = spy.categoryRegister(categoryRequirement, session);
		categoryRequirementInsertVerify(result);
		successMessageVerify();
	}
	
	@Test
	public void categoryRegister_post_exception_test() {
		categoryReqirementInsertWhen();
		exceptionWhen();
		when(categoryRequirementService.insert(categoryRequirement)).thenThrow(exception);
		Map<String, String> result = spy.categoryRegister(categoryRequirement, session);
		categoryRequirementInsertVerify(result);
		failMessageVerify("데이터 에러");
	}
	
	private void exceptionWhen(){
		when(exception.getMessage()).thenReturn("데이터 에러");
		failMessageWhen("데이터 에러");
	}

	private void categoryRequirementInsertVerify(Map<String, String> result) {
		existCategoryRequirementVerify(result);
		categoryRequirementInsertVerifySetting();
	}

	private void categoryRequirementInsertVerifySetting() {
		verify(categoryRequirementService,times(1)).insert(categoryRequirement);
		verify(categoryRequirement,times(1)).setAccountId(1);
	}

	private void categoryReqirementInsertWhen() {
		existCategoryRequirementWhen(false);	
		loginIdWhen();
	}

	private void successMessageVerify() {
		verify(spy,times(1)).getSuccessMessage();
	}

	private void successMessageWhen() {
		doReturn(expect).when(spy).getSuccessMessage();
	}
	
	private void existCategoryRequirementWhen(boolean result) {
		when(categoryRequirement.getLectureCategoryId()).thenReturn(1);
		doReturn(result).when(spy).existCategoryRequirement(1, session);
	}
	
	@Test
	public void existCategoryRequirement_exist_test() {
		loginIdWhen();
		when(categoryRequirementService.findByAccountAndCategoryId(1, 2)).thenReturn(categoryRequirement);
		boolean result = spy.existCategoryRequirement(2, session);
		loginIdVerify();
		verify(categoryRequirementService, times(1)).findByAccountAndCategoryId(1,2);
		assertTrue(result);
	}
	
	@Test
	public void existCategoryRequirement_not_exist_test() {
		loginIdWhen();
		when(categoryRequirementService.findByAccountAndCategoryId(1, 2)).thenReturn(null);
		boolean result = spy.existCategoryRequirement(2, session);
		loginIdVerify();
		verify(categoryRequirementService, times(1)).findByAccountAndCategoryId(1,2);
		assertFalse(result);
	}
	
	@Test
	public void categoryRequirement_delete_success_test() {
		successMessageWhen();
		Map<String, String> result = spy.categoryDelete(0);
		verify(categoryRequirementService, times(1)).delete(0);
		successMessageVerify();
		assertEquals(expect,result);
	}
	
	@Test
	public void categoryRequirement_delete_exception_test() {
		exceptionWhen();
		when(categoryRequirementService.delete(any(Integer.class))).thenThrow(exception);
		Map<String, String> result = spy.categoryDelete(0);
		verify(categoryRequirementService, times(1)).delete(0);
		failMessageVerify("데이터 에러");
		assertEquals(expect,result);
	}
	
	@Test
	public void categoryRequirement_update_get_success_test() {
		when(categoryRequirementService.findById(1)).thenReturn(categoryRequirement);
		when(categoryService.findAll()).thenReturn(categoryList);
		String result = spy.categoryUpdate(1, model,session);
		verify(categoryRequirementService,times(1)).findById(1);
		verify(categoryService, times(1)).findAll();
		verify(model,times(1)).addAttribute("categoryList", categoryList);
		verify(model,times(1)).addAttribute("requirement",categoryRequirement);
		assertEquals("/requirement/categoryUpdate", result);
	}
}
