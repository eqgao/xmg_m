package com._520it.crm.service;

import com._520it.crm.domain.Permission;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQuery;

import java.util.List;

public interface IPermissionService {
	int deleteByPrimaryKey(Long id);

	int insert(Permission record);

	Permission selectByPrimaryKey(Long id);

	List<Permission> selectAll();

	int updateByPrimaryKey(Permission record);

	List<Permission> getPermissionsByRoleId(Long rid);

	void load();

	 List<String> getResourceByEmployeeId(Long employeeId);

	//查询分页结果
	PageResult queryPageResult(EmployeeQuery qo);
}
