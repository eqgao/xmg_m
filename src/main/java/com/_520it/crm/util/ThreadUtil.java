package com._520it.crm.util;

import javax.servlet.http.HttpServletRequest;

public class ThreadUtil {
	public static ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<>();

	public static void setRequest(HttpServletRequest request) {
		threadLocal.set(request);
	}

	public static HttpServletRequest getRequest(){
		return threadLocal.get();
	}

}
