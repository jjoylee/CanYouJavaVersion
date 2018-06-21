package com.canyou.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.canyou.model.Account.AccountVO;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter{
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws IOException{
		HttpSession session = request.getSession(false);
		if(session == null) return redirectToLoginPage(response);
		if(session.getAttribute("loginAccount") == null)
			return redirectToLoginPage(response);
		request.setAttribute("link", request.getRequestURI());
		return true;
	}

	boolean redirectToLoginPage(HttpServletResponse response) throws IOException {
		response.sendRedirect("/account/login");
		return false;
	}
}
