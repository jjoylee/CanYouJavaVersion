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

import org.junit.Test;

public class LoginCheckInterceptorTest {
	
	@Test
	public void not_logged_in_test() throws IOException {
		LoginCheckInterceptor interceptor = new LoginCheckInterceptor();
		LoginCheckInterceptor spy = spy(interceptor);
		Object handler = mock(Object.class);
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		when(request.getSession(false)).thenReturn(null);
		doReturn(false).when(spy).redirectToLoginPage(response);
		boolean result = interceptor.preHandle(request, response, handler);
		verify(spy, times(1)).redirectToLoginPage(response);
	}
}
