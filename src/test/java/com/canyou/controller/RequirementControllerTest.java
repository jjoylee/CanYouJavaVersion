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
import com.canyou.model.LectureType.LectureTypeVO;
import com.canyou.model.LectureTypeRequirement.LectureTypeRequirementVO;
import com.canyou.service.LectureCategory.LectureCategoryService;
import com.canyou.service.LectureCategoryRequirement.LectureCategoryRequirementService;
import com.canyou.service.LectureType.LectureTypeService;
import com.canyou.service.LectureTypeRequirement.LectureTypeRequirementService;

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
	List<LectureTypeVO> typeList;
	LectureTypeRequirementService typeRequirementService;
	LectureTypeService typeService;
	
	@Before
	public void setUp() throws IllegalArgumentException, IllegalAccessException{
		MockGenerator.setMock(this);
		ctrl = new RequirementController();
		ctrl.categoryRequirementService = categoryRequirementService;
		ctrl.categoryService = categoryService;
		ctrl.typeService = typeService;
		ctrl.typeRequirementService = typeRequirementService;
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
	public void categoryRequirement_update_get_test() {
		when(categoryRequirementService.findById(1)).thenReturn(categoryRequirement);
		when(categoryService.findAll()).thenReturn(categoryList);
		String result = spy.categoryUpdate(1, model,session);
		verify(categoryRequirementService,times(1)).findById(1);
		verify(categoryService, times(1)).findAll();
		verify(model,times(1)).addAttribute("categoryList", categoryList);
		verify(model,times(1)).addAttribute("requirement",categoryRequirement);
		assertEquals("/requirement/categoryUpdate", result);
	}
	
	@Test
	public void updatedCategoryRequirementExist_exist() {
		LectureCategoryRequirementVO beforeRequirement = updatedCategoryRequirementsExistRequirementsWhen();
		doReturn(true).when(spy).existCategoryRequirement(3, session);
		boolean result = spy.updatedCategoryRequirementExist(1, categoryRequirement, session);
		updatedCategoryRequirementsExistRequirementsVerify(beforeRequirement);
		assertTrue(result);
	}
	
	@Test
	public void updatedCategoryRequirementExist_not_exist() {
		LectureCategoryRequirementVO beforeRequirement = updatedCategoryRequirementsExistRequirementsWhen();
		doReturn(false).when(spy).existCategoryRequirement(3, session);
		boolean result = spy.updatedCategoryRequirementExist(1, categoryRequirement, session);
		updatedCategoryRequirementsExistRequirementsVerify(beforeRequirement);
		assertFalse(result);
	}

	private void updatedCategoryRequirementsExistRequirementsVerify(LectureCategoryRequirementVO beforeRequirement) {
		verify(categoryRequirementService,times(1)).findById(1);
		verify(beforeRequirement,times(1)).getLectureCategoryId();
		verify(categoryRequirement,times(1)).getLectureCategoryId();
		verify(spy,times(1)).existCategoryRequirement(3, session);
	}

	private LectureCategoryRequirementVO updatedCategoryRequirementsExistRequirementsWhen() {
		LectureCategoryRequirementVO beforeRequirement = mock(LectureCategoryRequirementVO.class);
		when(categoryRequirementService.findById(1)).thenReturn(beforeRequirement);
		when(beforeRequirement.getLectureCategoryId()).thenReturn(2);
		when(categoryRequirement.getLectureCategoryId()).thenReturn(3);
		return beforeRequirement;
	}
	
	@Test
	public void categoryRequirement_post_update_fail_exist_test() {
		doReturn(true).when(spy).updatedCategoryRequirementExist(1, categoryRequirement, session);
		failMessageWhen("이미 존재합니다.");
		Map<String,String> result = spy.categoryUpdate(1, categoryRequirement, session);
		failMessageVerify("이미 존재합니다.");
		assertEquals(expect,result);
	}
	
	@Test
	public void categoryRequirement_post_update_success_test() {
		categoryRequirementPostUpdateWhen();
		successMessageWhen();
		Map<String, String> result = spy.categoryUpdate(2, categoryRequirement, session);
		categoryRequirementPostUpdateVerify();
		successMessageVerify();
		assertEquals(expect,result);
	}
	
	@Test
	public void categoryRequirement_post_update_fail_exception_test() {
		categoryRequirementPostUpdateWhen();
		exceptionWhen();
		when(categoryRequirementService.update(categoryRequirement)).thenThrow(exception);
		Map<String, String> result = spy.categoryUpdate(2, categoryRequirement, session);
		categoryRequirementPostUpdateVerify();
		failMessageVerify("데이터 에러");
		assertEquals(expect,result);
	}
	
	private void categoryRequirementPostUpdateWhen() {
		doReturn(false).when(spy).updatedCategoryRequirementExist(2, categoryRequirement, session);
		loginIdWhen();
	}

	private void categoryRequirementPostUpdateVerify() {
		verify(categoryRequirement, times(1)).setAccountId(1);
		verify(categoryRequirement, times(1)).setId(2);
		verify(categoryRequirementService, times(1)).update(categoryRequirement);
	}
	
	@Test
	public void type_get_test() {
		loginIdWhen();
		List<LectureTypeRequirementVO> requirements = mock(List.class); 
		when(typeRequirementService.findByAccountId(1)).thenReturn(requirements);
		String result = spy.type(model,session);
		loginIdVerify();
		verify(typeRequirementService, times(1)).findByAccountId(1);
		verify(model,times(1)).addAttribute("list", requirements);
		assertEquals("/requirement/type", result);
	}
	
	@Test
	public void typeRegister_get_test() {
		LectureCategoryVO category = getCategoryListWhen();
		getTypeListWhen(category);
		String result = ctrl.typeRegister(model);
		verify(categoryService,times(1)).findLectureTypeExist();
		verify(model,times(1)).addAttribute("categoryList", categoryList);
		verify(model,times(1)).addAttribute("typeList", typeList);
		verify(categoryList,times(1)).get(0);
		verify(category,times(1)).getId();
		assertEquals("/requirement/typeRegister",result);
	}

	private void getTypeListWhen(LectureCategoryVO category) {
		when(category.getId()).thenReturn(1);
		when(typeService.findByCategoryId(1)).thenReturn(typeList);
	}

	private LectureCategoryVO getCategoryListWhen() {
		LectureCategoryVO category = mock(LectureCategoryVO.class);
		when(categoryService.findLectureTypeExist()).thenReturn(categoryList);
		when(categoryList.get(0)).thenReturn(category);
		return category;
	}
	
	@Test
	public void typeRequirementExist_exist_test() {
		loginIdWhen();
		LectureTypeRequirementVO requirement = mock(LectureTypeRequirementVO.class);
		when(typeRequirementService.findByAccountAndTypeId(1, 2)).thenReturn(requirement);
		assertTrue(spy.existTypeRequirement(2, session));
		verify(typeRequirementService,times(1)).findByAccountAndTypeId(1, 2);
		loginIdVerify();
	}
	
	@Test
	public void typeRequirementExist_not_exist_test() {
		loginIdWhen();
		when(typeRequirementService.findByAccountAndTypeId(1, 2)).thenReturn(null);
		assertFalse(spy.existTypeRequirement(2, session));
		verify(typeRequirementService,times(1)).findByAccountAndTypeId(1, 2);
		loginIdVerify();
	}
	
	@Test
	public void typeRegister_post_test() {
		LectureCategoryVO category = getCategoryListWhen();
		getTypeListWhen(category);
		String result = ctrl.typeRegister(model);
		verify(categoryService,times(1)).findLectureTypeExist();
		verify(model,times(1)).addAttribute("categoryList", categoryList);
		verify(model,times(1)).addAttribute("typeList", typeList);
		verify(categoryList,times(1)).get(0);
		verify(category,times(1)).getId();
		assertEquals("/requirement/typeRegister",result);
	}
}
