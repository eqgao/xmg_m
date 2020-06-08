package com._520it.crm.web.controller;

import javax.servlet.http.HttpServletResponse;

import com._520it.crm.util.AjaxResult;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseController {
	/**
	 * 处理异常的方法
	 * @param response
	 * @throws Exception
	 */
	@ExceptionHandler(UnauthorizedException.class)
	public void handlerException(HandlerMethod method, HttpServletResponse response) throws Exception {
		//如果是ajax请求则返回json数据(如果有ResponseBody注解的就是ajax请求)
		ResponseBody annotation = method.getMethodAnnotation(ResponseBody.class);
		if (annotation != null) {
			response.setCharacterEncoding("utf-8");
			response.getWriter().println(new ObjectMapper().writeValueAsString(new AjaxResult(false,"没有权限!")));
		} else {
			//如果是访问页面或者资源就重定向到没有权限的页面
			response.sendRedirect("/nopermission.jsp");
		}
	}
}
