package com._520it.crm.web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com._520it.crm.domain.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class MyFormFilter extends FormAuthenticationFilter{

	/**
	 * 解决登录之后没有正常退出或注销,会出现错误
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		//判断是否是登录请求
		if(isLoginRequest(request, response)){
			//获取主体
			Subject subject = SecurityUtils.getSubject();
			//获取身份信息
			Object principal = subject.getPrincipal();
			if(principal!=null){
				Employee employee = (Employee)principal;
				//如果当前登录的用户名和主体的身份信息相同,则注销
				if(this.getUsername(request).equals(employee.getUsername())){
					subject.logout();
				}
			}
		}
		return super.isAccessAllowed(request, response, mappedValue);
	}

}
