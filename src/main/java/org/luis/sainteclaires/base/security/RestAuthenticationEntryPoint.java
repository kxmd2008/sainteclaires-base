package org.luis.sainteclaires.base.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component("loginUrlEntryPoint")
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		String targetUrl = null;
		String url = request.getRequestURI();
		if (url.endsWith("admin/login")) {
			return;
		}
		if (url.indexOf("jsp/admin") != -1 || url.indexOf("admin") != -1) {
			// 未登录而访问后台受控资源时，跳转到后台登录页面
			targetUrl = "/jsp/admin/login.jsp";
		} else {
			// 未登录而访问前台受控资源时，跳转到前台登录页面
			targetUrl = "/jsp/login.jsp";
		}
		targetUrl = request.getContextPath() + targetUrl;
		response.sendRedirect(targetUrl);
	}

}
