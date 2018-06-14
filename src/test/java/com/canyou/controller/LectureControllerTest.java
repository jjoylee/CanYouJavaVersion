package com.canyou.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.spy;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import com.canyou.model.Account.AccountVO;
import com.canyou.model.LectureDetail.LectureDetailVO;
import com.canyou.service.LectureDetail.LectureDetailService;


public class LectureControllerTest {
	
	LectureController ctrl;
	LectureController spy;
	LectureDetailService service;
	
	@Before 
	public void setUp(){
		ctrl = new LectureController();
		service = mock(LectureDetailService.class);
		ctrl.lectureDetailService = service;
		spy = spy(ctrl);
	}
	
	@Test
	public void list_test(){
		Model model = mock(Model.class);
		HttpSession session = mock(HttpSession.class);
		AccountVO account = mock(AccountVO.class);
		doReturn(account).when(spy).getLoginAccount(session);
		when(account.getId()).thenReturn(1);
		List<LectureDetailVO> list = mock(List.class);
		when(service.findByAccountId(any(Integer.class))).thenReturn(list);
		String result = spy.list(model, session);
		verify(service, times(1)).findByAccountId(1);
		verify(model,  times(1)).addAttribute("list",list);
		assertEquals("/lecture/list", result);
	}
}
