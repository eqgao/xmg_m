package com._520it.crm.service.impl;

import com._520it.crm.domain.Permission;
import com._520it.crm.mapper.PermissionMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQuery;
import com._520it.crm.service.IPermissionService;
import com._520it.crm.util.PermissionName;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService,ApplicationContextAware{
	@Autowired
	private PermissionMapper mapper;

	private ApplicationContext ac;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ac = applicationContext;
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Permission record) {
		return mapper.insert(record);
	}

	@Override
	public Permission selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Permission> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Permission record) {
		return mapper.updateByPrimaryKey(record);
	}

	public List<Permission> getPermissionsByRoleId(Long rid){
		return mapper.getPermissionsByRoleId(rid);
	}

	/**
	 * 加载权限
	 */
	@Override
	public void load() {
		//获取所有数据库中已经存在的权限表达式
		List<String> allResource = mapper.getAllResource();

		//获取拥有Controller注解的bean
		Map<String, Object> map = ac.getBeansWithAnnotation(Controller.class);
		System.out.println(map);
		//获取所有的controller
		Collection<Object> values = map.values();
		//获取每一个controller
		for (Object controller : values) {
			System.out.println(controller.getClass());
			//获取当前对象声明的所有方法
			Method[] methods = controller.getClass().getSuperclass().getDeclaredMethods();
			//判断当前方法是否有对应注解@RequiresPermissions
			for (Method method : methods) {
				RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
				if(requiresPermissions==null){
					//结束本次循坏,执行下一次循坏
					continue;
				}
				//获取当前方法的权限表达式数组
				String[] value = requiresPermissions.value();
				String resource = StringUtils.join(value,",");

				//判断该表达式是否已经存在数据库
				if(!allResource.contains(resource)){

					//获取PermissionName注解的值
					PermissionName permissionName = method.getAnnotation(PermissionName.class);

					Permission permission = new Permission();
					//设置权限名
					permission.setName(permissionName.value());

					//设置权限表达式
					permission.setResource(resource);

					//插入到数据库中
					insert(permission);
				}
			}

		}


	}

	@Override
	public List<String> getResourceByEmployeeId(Long employeeId) {
		return mapper.getResourceByEmployeeId(employeeId);
	}

	@Override
	public PageResult queryPageResult(EmployeeQuery qo) {
		//查询总结果数
		Long count = mapper.queryPageCount(qo);
		if (count == 0) {
			return new PageResult(count, Collections.EMPTY_LIST);
		}

		//查询结果集
		return new PageResult(count, mapper.queryPageResult(qo));
	}


}
