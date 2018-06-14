package com.canyou.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import static org.mockito.Mockito.spy;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.canyou.model.Account.AccountVO;
import com.canyou.model.LectureDetail.LectureDetailVO;
import com.canyou.service.LectureDetail.LectureDetailService;


public class LectureControllerTest {
	
	LectureController ctrl;
	
	@Before 
	public void setUp(){
		ctrl = new LectureController();
	}
	
	@Test
	public void list_test(){
		LectureDetailService service = mock(LectureDetailService.class);
		ctrl.lectureDetailService = service;
		Model model = mock(Model.class);
		AccountVO account = mock(AccountVO.class);
		List<LectureDetailVO> list = mock(List.class);
		when(service.findByAccountId(any(Integer.class))).thenReturn(list);
	
		String result = ctrl.list(model);
		verify(service, times(1)).findByAccountId(any(Integer.class));
		verify(model,  times(1)).addAttribute("list",list);
		assertEquals("/lecture/list", result);
	}
}
