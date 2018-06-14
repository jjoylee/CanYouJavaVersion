package com.canyou.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import com.canyou.model.Account.AccountVO;

public class LoginCheckInterceptorTest {
	
	LoginCheckInterceptor interceptor;
	LoginCheckInterceptor spy;
	Object handler;
	HttpServletRequest request;
	HttpServletResponse response;
	boolean result;
	
	@Before
	public void setUp(){
		interceptor = new LoginCheckInterceptor();
		spy = spy(interceptor);
		handler = mock(Object.class);
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
	}
	
	@Test
	public void redirectToLoginPage_test() throws IOException{
		result = interceptor.redirectToLoginPage(response);
		assertFalse(result);
		verify(response,times(1)).sendRedirect("/account/login");
	}
	
	@Test
	public void session_not_exist_test() throws IOException {
		when(request.getSession(false)).thenReturn(null);
		doReturn(false).when(spy).redirectToLoginPage(response);
		result = spy.preHandle(request, response, handler);
		verify(spy, times(1)).redirectToLoginPage(response);
		assertFalse(result);
	}
	
	@Test
	public void not_logged_in_test() throws IOException {
		HttpSession session = mock(HttpSession.class);
		when(request.getSession(false)).thenReturn(session);
		when(session.getAttribute("loginAccount")).thenReturn(null);
		doReturn(false).when(spy).redirectToLoginPage(response);
		result = spy.preHandle(request, response, handler);
		verify(spy, times(1)).redirectToLoginPage(response);
		assertFalse(result);
	}
	
	@Test
	public void login_success_test() throws IOException {
		HttpSession session = mock(HttpSession.class);
		when(request.getSession(false)).thenReturn(session);
		when(session.getAttribute("loginAccount")).thenReturn(mock(Object.class));
		result = interceptor.preHandle(request, response, handler);
		assertTrue(result);
	}
}
