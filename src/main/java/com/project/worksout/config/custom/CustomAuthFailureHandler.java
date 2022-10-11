package com.project.worksout.config.custom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthFailureHandler implements AuthenticationFailureHandler{
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		System.out.println(exception.getMessage());
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charse=utf-8");
		response.getWriter().print("<html><head></head><body><script>alert(\"로그인실패\\n 아이디. 비밀번호를 확인해주세요. \");history.back();</script></body></html>");
		
	}

}
