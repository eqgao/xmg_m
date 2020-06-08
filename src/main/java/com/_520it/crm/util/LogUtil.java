package com._520it.crm.util;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Systemlog;
import com._520it.crm.service.ISystemlogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class LogUtil {

	@Autowired
	private ISystemlogService systemlogService;
	/**
	 * 写入日志
	 * @throws JsonProcessingException
	 */
	public void writeLog(JoinPoint joinPoint) throws JsonProcessingException{
		//如果是日志的service,就不保存
		if(joinPoint.getTarget() instanceof ISystemlogService){
			return;
		}
		Systemlog systemlog = new Systemlog();
		//操作时间
		systemlog.setOptime(new Date());
		//操作用户
		Object employee = SecurityUtils.getSubject().getPrincipal();
		if(employee!=null){
			systemlog.setOpuser((Employee)employee);
		}
		//com.xxx.employeeserviceimpl:save
		//获取目标执行的类名
		String target = joinPoint.getTarget().getClass().getName();
		System.out.println(target);
		String method = joinPoint.getSignature().getName();
		System.out.println(method);
		//使用功能
		systemlog.setFunction(target+":"+method);
		//调用时传入的参数
		Object[] args = joinPoint.getArgs();
		systemlog.setParams(new ObjectMapper().writeValueAsString(args));

		//获取与当前线程绑定在一起请求对象
		HttpServletRequest request = ThreadUtil.getRequest();
		if(request!=null){
			//先获取到请求对象
			systemlog.setOpip(request.getRemoteHost());
		}
		System.out.println(systemlog);
		//保存日志
		systemlogService.insert(systemlog);

	}
}
