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
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;

import com.canyou.model.LectureCategory.LectureCategoryVO;
import com.canyou.model.LectureCategoryRequirement.LectureCategoryRequirementVO;
import com.canyou.model.LectureType.LectureTypeVO;
import com.canyou.model.LectureTypeRequirement.LectureTypeRequirementVO;
import com.canyou.model.ScoreRequirement.ScoreRequirementVO;
import com.canyou.model.SectionRequirement.SectionRequirementVO;
import com.canyou.service.LectureCategory.LectureCategoryService;
import com.canyou.service.LectureCategoryRequirement.LectureCategoryRequirementService;
import com.canyou.service.LectureType.LectureTypeService;
import com.canyou.service.LectureTypeRequirement.LectureTypeRequirementService;
import com.canyou.service.ScoreRequirement.ScoreRequirementService;
import com.canyou.service.SectionRequirement.SectionRequirementService;

public class RequirementControllerTest {
	
	RequirementController ctrl;
	RequirementController spy;
	Model model;
	HttpSession session;
	LectureCategoryRequirementService categoryRequirementService;
	LectureCategoryService categoryService;
	LectureCategoryRequirementVO categoryRequirement;
	LectureTypeRequirementVO typeRequirement;
	SectionRequirementVO sectionRequirement;
	ScoreRequirementVO scoreRequirement;
	Map<String, String> expect;
	DataAccessException exception;
	List<LectureCategoryVO> categoryList;
	List<LectureTypeVO> typeList;
	LectureTypeRequirementService typeRequirementService;
	LectureTypeService typeService;
	SectionRequirementService sectionRequirementService;
	ScoreRequirementService scoreRequirementService;
	
	@Before
	public void setUp() throws IllegalArgumentException, IllegalAccessException{
		MockGenerator.setMock(this);
		ctrl = new RequirementController();
		ctrl.categoryRequirementService = categoryRequirementService;
		ctrl.categoryService = categoryService;
		ctrl.typeService = typeService;
		ctrl.typeRequirementService = typeRequirementService;
		ctrl.sectionRequirementService = sectionRequirementService;
		ctrl.scoreRequirementService = scoreRequirementService;
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
		verify(spy,times(1)).failMessage(message);
	}

	private void failMessageWhen(String message) {
		doReturn(expect).when(spy).failMessage(message);
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
		verify(spy,times(1)).successMessage();
	}

	private void successMessageWhen() {
		doReturn(expect).when(spy).successMessage();
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
	public void categoryRequirement_delete_fail_notAuthorized_test() {
		doReturn(false).when(spy).isAuthorizedCategory(0, session);
		failMessageWhen("접근 불가능한 페이지입니다.");
		Map<String, String> result = spy.categoryDelete(0,session);
		failMessageVerify("접근 불가능한 페이지입니다.");
		assertEquals(expect,result);
	}
	
	@Test
	public void categoryRequirement_delete_success_test() {
		doReturn(true).when(spy).isAuthorizedCategory(0, session);
		successMessageWhen();
		Map<String, String> result = spy.categoryDelete(0, session);
		verify(categoryRequirementService, times(1)).delete(0);
		successMessageVerify();
		assertEquals(expect,result);
	}
	
	@Test
	public void categoryRequirement_delete_exception_test() {
		doReturn(true).when(spy).isAuthorizedCategory(0, session);
		exceptionWhen();
		when(categoryRequirementService.delete(any(Integer.class))).thenThrow(exception);
		Map<String, String> result = spy.categoryDelete(0,session);
		verify(categoryRequirementService, times(1)).delete(0);
		failMessageVerify("데이터 에러");
		assertEquals(expect,result);
	}
	
	@Test
	public void categoryRequirement_update_get_not_authorized_test() {
		doReturn(false).when(spy).isAuthorizedCategory(1, session);
		String result = spy.categoryUpdate(1, model,session);
		verify(spy,times(1)).isAuthorizedCategory(1, session);
		assertEquals("redirect:/requirement/category", result);
	}
	
	@Test
	public void categoryRequirement_update_get_success_test() {
		doReturn(true).when(spy).isAuthorizedCategory(1, session);
		when(categoryRequirementService.findById(1)).thenReturn(categoryRequirement);
		when(categoryService.findAll()).thenReturn(categoryList);
		String result = spy.categoryUpdate(1, model,session);
		verify(spy,times(1)).isAuthorizedCategory(1, session);
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
	public void isAuthorizedCategory_true_test() {
		when(categoryRequirementService.findById(1)).thenReturn(categoryRequirement);
		when(categoryRequirement.getAccountId()).thenReturn(1);
		loginIdWhen();
		assertTrue(spy.isAuthorizedCategory(1, session));
		verify(categoryRequirementService, times(1)).findById(1);
		verify(categoryRequirement,times(1)).getAccountId();
		loginIdVerify();
	}
	
	@Test
	public void isAuthorizedCategory_false_test() {
		when(categoryRequirementService.findById(1)).thenReturn(categoryRequirement);
		when(categoryRequirement.getAccountId()).thenReturn(2);
		loginIdWhen();
		assertFalse(spy.isAuthorizedCategory(1, session));
		verify(categoryRequirementService, times(1)).findById(1);
		verify(categoryRequirement,times(1)).getAccountId();
		loginIdVerify();
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
		when(typeRequirementService.findByAccountAndTypeId(1, 2)).thenReturn(typeRequirement);
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
	public void typeRegister_post_fail_already_exist_test() {
		existTypeRequirementWhen(true);
		failMessageWhen("이미 존재합니다.");
		Map<String,String> result = spy.typeRegister(typeRequirement, session);
		verify(typeRequirement,times(1)).getLectureTypeId();
		failMessageVerify("이미 존재합니다.");
		assertEquals(expect, result);
	}

	private void existTypeRequirementWhen(boolean result) {
		when(typeRequirement.getLectureTypeId()).thenReturn(1);
		doReturn(result).when(spy).existTypeRequirement(1, session);
	}
	
	@Test
	public void typeRegister_post_success_test() {
		typeRegisterInsertWhen();
		Map<String,String> result = spy.typeRegister(typeRequirement, session);
		typeRegisterInsertVerify();
		successMessageVerify();
		assertEquals(expect, result);
	}
	
	@Test
	public void typeRegister_post_fail_exception_test() {
		typeRegisterInsertWhen();
		exceptionWhen();
		when(typeRequirementService.insert(typeRequirement)).thenThrow(exception);
		Map<String,String> result = spy.typeRegister(typeRequirement, session);
		typeRegisterInsertVerify();
		failMessageVerify("데이터 에러");
		assertEquals(expect, result);
	}

	private void typeRegisterInsertVerify() {
		loginIdVerify();
		verify(typeRequirement,times(1)).setAccountId(1);
		verify(typeRequirementService,times(1)).insert(typeRequirement);
		verify(typeRequirement,times(1)).getLectureTypeId();
	}

	private void typeRegisterInsertWhen() {
		existTypeRequirementWhen(false);
		loginIdWhen();
		successMessageWhen();
	}
	
	@Test
	public void isAuthorizedType_true_test(){
		when(typeRequirementService.findById(1)).thenReturn(typeRequirement);
		when(typeRequirement.getAccountId()).thenReturn(1);
		loginIdWhen();
		assertTrue(spy.isAuthorizedType(1, session));
		verify(typeRequirementService,times(1)).findById(1);
		verify(typeRequirement,times(1)).getAccountId();
		loginIdVerify();
	}
	
	@Test
	public void typeDelete_success_test() {
		doReturn(true).when(spy).isAuthorizedType(1, session);
		successMessageWhen();
		Map<String,String> result = spy.typeDelete(1, session);
		verify(spy,times(1)).isAuthorizedType(1, session);
		verify(typeRequirementService,times(1)).delete(1);
		successMessageVerify();
		assertEquals(expect, result);
	}
	
	@Test
	public void typeDelete_fail_not_authorized_test() {
		doReturn(false).when(spy).isAuthorizedType(1, session);
		failMessageWhen("접근 불가능한 페이지입니다.");
		Map<String, String> result = spy.typeDelete(1, session);
		verify(spy,times(1)).isAuthorizedType(1, session);
		failMessageVerify("접근 불가능한 페이지입니다.");
		assertEquals(expect, result);
	}
	
	@Test
	public void typeDelete_fail_exception_test() {
		doReturn(true).when(spy).isAuthorizedType(1, session);
		exceptionWhen();
		when(typeRequirementService.delete(1)).thenThrow(exception);
		Map<String,String> result = spy.typeDelete(1, session);
		verify(spy,times(1)).isAuthorizedType(1, session);
		verify(typeRequirementService,times(1)).delete(1);
		failMessageVerify("데이터 에러");
		assertEquals(expect, result);
	}
	
	@Test
	public void typeUpdate_get_not_authorized_test() {
		doReturn(false).when(spy).isAuthorizedType(1, session);
		String result = spy.typeUpdate(1, session, model);
		verify(spy,times(1)).isAuthorizedType(1, session);
		assertEquals("redirect:/requirement/type",result);
	}
	
	@Test
	public void typeUpdate_get_success_test() {
		doReturn(true).when(spy).isAuthorizedType(1, session);
		when(typeRequirementService.findById(1)).thenReturn(typeRequirement);
		when(categoryService.findLectureTypeExist()).thenReturn(categoryList);
		when(typeRequirement.getLectureCategoryId()).thenReturn(2);
		when(typeService.findByCategoryId(2)).thenReturn(typeList);
		assertEquals("/requirement/typeUpdate",spy.typeUpdate(1, session, model));
		verify(spy,times(1)).isAuthorizedType(1, session);
		verify(typeRequirementService,times(1)).findById(1);
		verify(categoryService,times(1)).findLectureTypeExist();
		verify(typeRequirement,times(1)).getLectureCategoryId();
		verify(typeService,times(1)).findByCategoryId(2);
		verify(model,times(1)).addAttribute("categoryList",categoryList);
		verify(model,times(1)).addAttribute("typeList", typeList);
		verify(model,times(1)).addAttribute("requirement",typeRequirement);
	}
	
	@Test
	public void updatedTypeExist_test(){
		LectureTypeRequirementVO before = mock(LectureTypeRequirementVO.class);
		when(typeRequirementService.findById(1)).thenReturn(before);
		when(before.getLectureTypeId()).thenReturn(2);
		when(typeRequirement.getLectureTypeId()).thenReturn(3);
		doReturn(true).when(spy).existTypeRequirement(3, session);
		assertTrue(spy.updatedTypeExist(1, typeRequirement, session));
		verify(typeRequirementService,times(1)).findById(1);
		verify(before,times(1)).getLectureTypeId();
		verify(typeRequirement,times(1)).getLectureTypeId();
		verify(spy,times(1)).existTypeRequirement(3, session);
	}
	
	@Test
	public void typeUpdate_post_fail_alreadyExist_test() {
		doReturn(true).when(spy).updatedTypeExist(1, typeRequirement, session);
		failMessageWhen("이미 존재합니다.");
		Map<String, String> result = spy.typeUpdate(1, session, typeRequirement);
		verify(spy,times(1)).updatedTypeExist(1, typeRequirement, session);
		failMessageVerify("이미 존재합니다.");
		assertEquals(expect,result);
	}
	
	@Test
	public void typeUpdate_post_success_test() {
		typeUpdateWhen();
		successMessageWhen();
		Map<String, String> result = spy.typeUpdate(1, session, typeRequirement);
		typeUpdateVerify();
		successMessageVerify();
		assertEquals(expect,result);
	}

	private void typeUpdateVerify() {
		verify(spy,times(1)).updatedTypeExist(1, typeRequirement, session);
		verify(typeRequirement,times(1)).setAccountId(1);
		verify(typeRequirement,times(1)).setId(1);
		verify(typeRequirementService,times(1)).update(typeRequirement);
	}

	private void typeUpdateWhen() {
		doReturn(false).when(spy).updatedTypeExist(1, typeRequirement, session);
		loginIdWhen();
	}
	
	@Test
	public void typeUpdate_post_fail_exception_test() {
		typeUpdateWhen();
		exceptionWhen();
		when(typeRequirementService.update(typeRequirement)).thenThrow(exception);
		Map<String, String> result = spy.typeUpdate(1, session, typeRequirement);
		typeUpdateVerify();
		failMessageVerify("데이터 에러");
		assertEquals(expect,result);
	}
	
	@Test
	public void section_test() {
		List<SectionRequirementVO> requirement = mock(List.class);
		loginIdWhen();
		when(sectionRequirementService.findByAccountId(1)).thenReturn(requirement);
		assertEquals("/requirement/section",spy.section(session, model));
		loginIdVerify();
		verify(sectionRequirementService,times(1)).findByAccountId(1);
		verify(model,times(1)).addAttribute("list",requirement);
	}
	
	@Test
	public void sectionRegister_get_exist_test() {
		loginIdWhen();
		doReturn(true).when(spy).existSectionRequirement(1);
		assertEquals("redirect:/requirement/section",spy.sectionRegister(session));
		verify(spy,times(1)).existSectionRequirement(1);
		loginIdVerify();
	}
	
	@Test
	public void sectionRegister_get_test() {
		loginIdWhen();
		doReturn(false).when(spy).existSectionRequirement(1);
		assertEquals("/requirement/sectionRegister",spy.sectionRegister(session));
		verify(spy,times(1)).existSectionRequirement(1);
		loginIdVerify();
	}
	
	@Test
	public void existSectionRequirement_true_test() {
		when(sectionRequirementService.findByAccountIdForCheck(1)).thenReturn(sectionRequirement);
		assertTrue(spy.existSectionRequirement(1));
		verify(sectionRequirementService,times(1)).findByAccountIdForCheck(1);
	}
	
	@Test
	public void existSectionRequirement_false_test() {
		assertFalse(spy.existSectionRequirement(1));
		when(sectionRequirementService.findByAccountIdForCheck(1)).thenReturn(null);
		verify(sectionRequirementService,times(1)).findByAccountIdForCheck(1);
	}
	
	@Test
	public void sectionRegister_post_success_test() {
		loginIdWhen();
		successMessageWhen();
		assertEquals(expect,spy.sectionRegister(sectionRequirement, session));
		sectionRegisterInsertVerify();
		successMessageVerify();
	}
	
	@Test
	public void sectionRegister_post_fail_exception_test() {
		loginIdWhen();
		exceptionWhen();
		when(sectionRequirementService.insert(sectionRequirement)).thenThrow(exception);
		assertEquals(expect,spy.sectionRegister(sectionRequirement, session));
		sectionRegisterInsertVerify();
		failMessageVerify("데이터 에러");
	}

	private void sectionRegisterInsertVerify() {
		verify(sectionRequirement,times(1)).setAccountId(1);
		verify(sectionRequirement,times(1)).setLectureTypeId(2);
		verify(sectionRequirementService,times(1)).insert(sectionRequirement);
	}
	
	@Test
	public void isAuthorizedSection_true_test() {
		when(sectionRequirementService.findById(1)).thenReturn(sectionRequirement);
		loginIdWhen();
		when(sectionRequirement.getAccountId()).thenReturn(1);
		assertTrue(spy.isAuthorizedSection(1, session));
		verify(sectionRequirementService,times(1)).findById(1);
		loginIdVerify();
		verify(sectionRequirement,times(1)).getAccountId();
	}
	
	@Test
	public void isAuthorizedSection_false_test() {
		when(sectionRequirementService.findById(1)).thenReturn(sectionRequirement);
		loginIdWhen();
		when(sectionRequirement.getAccountId()).thenReturn(2);
		assertFalse(spy.isAuthorizedSection(1, session));
		verify(sectionRequirementService,times(1)).findById(1);
		loginIdVerify();
		verify(sectionRequirement,times(1)).getAccountId();
	}
	
	@Test
	public void sectionUpdate_get_notAuthorized_test() {
		doReturn(false).when(spy).isAuthorizedSection(1, session);
		assertEquals("redirect:/requirement/section",spy.sectionUpdate(1, session, model));
		verify(spy,times(1)).isAuthorizedSection(1, session);
	}
	
	@Test
	public void sectionUpdate_get_authorized_test() {
		doReturn(true).when(spy).isAuthorizedSection(1, session);
		when(sectionRequirementService.findById(1)).thenReturn(sectionRequirement);
		assertEquals("/requirement/sectionUpdate",spy.sectionUpdate(1, session, model));
		verify(spy,times(1)).isAuthorizedSection(1, session);
		verify(sectionRequirementService,times(1)).findById(1);
		verify(model,times(1)).addAttribute("requirement",sectionRequirement);
	}
	
	@Test
	public void sectionUpdate_post_success_test() {
		loginIdWhen();
		successMessageWhen();
		assertEquals(expect,spy.sectionUpdate(2, sectionRequirement, session));
		sectionRequirementUpdateVerify();
		successMessageVerify();
	}

	private void sectionRequirementUpdateVerify() {
		verify(sectionRequirement,times(1)).setAccountId(1);
		verify(sectionRequirement,times(1)).setId(2);
		verify(sectionRequirement,times(1)).setLectureTypeId(2);
		verify(sectionRequirementService,times(1)).update(sectionRequirement);
		
	}
	
	@Test
	public void sectionUpdate_post_fail_exception_test() {
		loginIdWhen();
		exceptionWhen();
		when(sectionRequirementService.update(sectionRequirement)).thenThrow(exception);
		assertEquals(expect,spy.sectionUpdate(2, sectionRequirement, session));
		sectionRequirementUpdateVerify();
		failMessageVerify("데이터 에러");
	}
	
	@Test
	public void sectionDelete_fail_notAuthorized_test(){
		doReturn(false).when(spy).isAuthorizedSection(1, session);
		failMessageWhen("접근 불가능한 페이지입니다.");
		assertEquals(expect, spy.sectionDelete(1, session));
		verify(spy,times(1)).isAuthorizedSection(1, session);
		failMessageVerify("접근 불가능한 페이지입니다.");
	}
	
	@Test
	public void sectionDelete_success_test(){
		doReturn(true).when(spy).isAuthorizedSection(1, session);
		successMessageWhen();
		assertEquals(expect, spy.sectionDelete(1, session));
		verify(spy,times(1)).isAuthorizedSection(1, session);
		verify(sectionRequirementService,times(1)).delete(1);
		successMessageVerify();
	}
	
	@Test
	public void scoreRequirement_get_test(){
		List<ScoreRequirementVO> list = mock(List.class);
		loginIdWhen();
		when(scoreRequirementService.findByAccountId(1)).thenReturn(list);
		assertEquals("/requirement/score",spy.score(session, model));
		loginIdVerify();
		verify(scoreRequirementService,times(1)).findByAccountId(1);
		verify(model,times(1)).addAttribute("list",list);
	}
	
	@Test
	public void existScoreRequirement_true_test(){
		when(scoreRequirementService.findByAccountIdForCheck(1)).thenReturn(scoreRequirement);
		assertTrue(ctrl.existScoreRequirement(1));
		verify(scoreRequirementService,times(1)).findByAccountIdForCheck(1);
	}
	
	@Test
	public void existScoreRequirement_false_test(){
		when(scoreRequirementService.findByAccountIdForCheck(1)).thenReturn(null);
		assertFalse(ctrl.existScoreRequirement(1));
		verify(scoreRequirementService,times(1)).findByAccountIdForCheck(1);
	}
	
	@Test
	public void scoreRegister_get_exist_test(){
		loginIdWhen();
		doReturn(true).when(spy).existScoreRequirement(1);
		assertEquals("redirect:/requirement/score",spy.scoreRegister(session));
		loginIdVerify();
		verify(spy,times(1)).existScoreRequirement(1);
	}
	
	@Test
	public void scoreRegister_get_notexist_test(){
		loginIdWhen();
		doReturn(false).when(spy).existScoreRequirement(1);
		assertEquals("/requirement/scoreRegister",spy.scoreRegister(session));
		loginIdVerify();
		verify(spy,times(1)).existScoreRequirement(1);
	}
	
	@Test
	public void isAuthorizedScore_authorized_test(){
		when(scoreRequirementService.findById(1)).thenReturn(scoreRequirement);
		when(scoreRequirement.getAccountId()).thenReturn(1);
		loginIdWhen();
		assertTrue(spy.isAuthorizedScore(1, session));
		loginIdVerify();
		verify(scoreRequirementService,times(1)).findById(1);
		verify(scoreRequirement,times(1)).getAccountId();
	}
	
	@Test
	public void isAuthorizedScore_notAuthorized_test(){
		when(scoreRequirementService.findById(1)).thenReturn(scoreRequirement);
		when(scoreRequirement.getAccountId()).thenReturn(2);
		loginIdWhen();
		assertFalse(spy.isAuthorizedScore(1, session));
		loginIdVerify();
		verify(scoreRequirementService,times(1)).findById(1);
		verify(scoreRequirement,times(1)).getAccountId();
	}
	
	@Test
	public void scoreRegister_post_success_test(){
		loginIdWhen();
		successMessageWhen();
		assertEquals(expect,spy.scoreRegister(scoreRequirement, session));
		scoreRequirementInsertVerfiy();
		successMessageVerify();
	}

	private void scoreRequirementInsertVerfiy() {
		loginIdVerify();
		verify(scoreRequirement,times(1)).setAccountId(1);
		verify(scoreRequirementService,times(1)).insert(scoreRequirement);
	}
	
	@Test
	public void scoreRegister_post_fail_exception_test(){
		loginIdWhen();
		exceptionWhen();
		when(scoreRequirementService.insert(scoreRequirement)).thenThrow(exception);
		assertEquals(expect,spy.scoreRegister(scoreRequirement, session));
		scoreRequirementInsertVerfiy();
		failMessageVerify("데이터 에러");
	}
	
	@Test
	public void scoreUpdate_get_notAuthorized_test(){
		doReturn(false).when(spy).isAuthorizedScore(1, session);
		assertEquals("redirect:/requirement/score",spy.scoreUpdate(1, session, model));
		verify(spy,times(1)).isAuthorizedScore(1, session);
	}
	
	@Test
	public void scoreUpdate_get_authorized_test(){
		doReturn(true).when(spy).isAuthorizedScore(1, session);
		when(scoreRequirementService.findById(1)).thenReturn(scoreRequirement);
		assertEquals("/requirement/scoreUpdate",spy.scoreUpdate(1, session, model));
		verify(spy,times(1)).isAuthorizedScore(1, session);
		verify(scoreRequirementService,times(1)).findById(1);
		verify(model,times(1)).addAttribute("requirement", scoreRequirement);
	}
	
	@Test
	public void scoreUpdate_post_success_test(){
		loginIdWhen();
		successMessageWhen();
		assertEquals(expect,spy.scoreUpdate(1, session, scoreRequirement));
		scoreRequirementUpdateVerify();
		successMessageVerify();
	}

	private void scoreRequirementUpdateVerify() {
		loginIdVerify();
		verify(scoreRequirement,times(1)).setAccountId(1);
		verify(scoreRequirementService,times(1)).update(scoreRequirement);
	}
	
	@Test
	public void scoreUpdate_post_fail_exception_test(){
		loginIdWhen();
		exceptionWhen();
		when(scoreRequirementService.update(scoreRequirement)).thenThrow(exception);
		assertEquals(expect,spy.scoreUpdate(1, session, scoreRequirement));
		scoreRequirementUpdateVerify();
		failMessageVerify("데이터 에러");
	}
	
	@Test
	public void scoreDelete_fail_notAuthorized_test(){
		doReturn(false).when(spy).isAuthorizedScore(1, session);
		failMessageWhen("접근 불가능한 페이지입니다.");
		assertEquals(expect, spy.scoreDelete(1, session));
		verify(spy,times(1)).isAuthorizedScore(1, session);
		failMessageVerify("접근 불가능한 페이지입니다.");
	}
	
	@Test
	public void scoreDelete_success_test(){
		doReturn(true).when(spy).isAuthorizedScore(1, session);
		successMessageWhen();
		assertEquals(expect, spy.scoreDelete(1, session));
		scoreRequirementDeleteVerify();
		successMessageVerify();
	}

	private void scoreRequirementDeleteVerify() {
		verify(spy,times(1)).isAuthorizedScore(1, session);
		verify(scoreRequirementService,times(1)).delete(1);
	}
	
	@Test
	public void scoreDelete_fail_exception_test(){
		doReturn(true).when(spy).isAuthorizedScore(1, session);
		exceptionWhen();
		when(scoreRequirementService.delete(1)).thenThrow(exception);
		assertEquals(expect, spy.scoreDelete(1, session));
		scoreRequirementDeleteVerify();
		failMessageVerify("데이터 에러");
	}
}
