package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.List;

import com._520it.crm.query.EmployeeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.Permission;
import com._520it.crm.domain.Role;
import com._520it.crm.mapper.RoleMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{
	@Autowired
	private RoleMapper mapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		//先删除中间表关系
		mapper.deleteRelation(id);
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Role record) {
		//先保存角色,才有id,才能将进行关联
		int count = mapper.insert(record);

		List<Permission> permissions = record.getPermissions();
		//判断是否有权限集合
		if(permissions.size()>0){
			for (Permission permission : permissions) {
				//处理中间表
				mapper.insertRelation(record.getId(), permission.getId());
			}
		}

		return count;
	}

	@Override
	public Role selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Role> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Role record) {
		//先删除中间表关系
		mapper.deleteRelation(record.getId());

		//重新关联关系
		List<Permission> permissions = record.getPermissions();
		//判断是否有权限集合
		if(permissions.size()>0){
			for (Permission permission : permissions) {
				//处理中间表
				mapper.insertRelation(record.getId(), permission.getId());
			}
		}

		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPageResult(EmployeeQuery qo) {
		//查询总结果数
		Long count = mapper.queryPageCount(qo);
		if(count==0){
			return new PageResult(count,Collections.EMPTY_LIST);
		}

		//查询结果集
		return new PageResult(count, mapper.queryPageResult(qo));
	}

	@Override
	public List<Long> getRolesIdByEmployeeId(Long employeeId) {
		return mapper.getRolesIdByEmployeeId(employeeId);
	}

	@Override
	public List<String> getRoleSnByEmployeeId(Long employeeId) {
		return mapper.getRoleSnByEmployeeId(employeeId);
	}

}
