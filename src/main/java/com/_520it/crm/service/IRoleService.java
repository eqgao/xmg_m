package com._520it.crm.service;

import com._520it.crm.domain.Role;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQuery;

import java.util.List;

public interface IRoleService {
	int deleteByPrimaryKey(Long id);

	int insert(Role record);

	Role selectByPrimaryKey(Long id);

	List<Role> selectAll();

	int updateByPrimaryKey(Role record);

	//查询分页结果
	PageResult queryPageResult(EmployeeQuery qo);

	List<Long> getRolesIdByEmployeeId(Long employeeId);

    List<String> getRoleSnByEmployeeId(Long employeeId);

}
