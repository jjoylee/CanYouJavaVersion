package com.canyou.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
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
		doReturn(1).when(spy).loginId(session);
		when(categoryRequirementService.findByAccountId(1)).thenReturn(list);
		String result = spy.category(model, session);
		verify(categoryRequirementService, times(1)).findByAccountId(1);
		verify(spy,times(1)).loginId(session);
		verify(model, times(1)).addAttribute("list", list);
		assertEquals("/requirement/category", result);
	}
	
	@Test
	public void categoryRegister_get_test() {
		List<LectureCategoryVO> list = mock(List.class);
		when(categoryService.findAll()).thenReturn(list);
		String result = ctrl.categoryRegister(model);
		verify(categoryService, times(1)).findAll();
		verify(model,times(1)).addAttribute("list",list);
		assertEquals("/requirement/categoryRegister", result);
	}
}
