package com._520it.crm.mapper;

import com._520it.crm.domain.Permission;
import com._520it.crm.query.EmployeeQuery;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    List<Permission> getPermissionsByRoleId(Long rid);

    List<String> getAllResource();

    List<String> getResourceByEmployeeId(Long employeeId);

    Long queryPageCount(EmployeeQuery qo);

    List<Permission> queryPageResult(EmployeeQuery qo);
}