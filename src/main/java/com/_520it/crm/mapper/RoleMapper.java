package com._520it.crm.mapper;

import com._520it.crm.domain.Role;
import com._520it.crm.query.EmployeeQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    void insertRelation(@Param("roleId")Long roleId,@Param("permissionId")Long permissionId);

    void deleteRelation(Long roleId);

    Long queryPageCount(EmployeeQuery qo);

    List<Role> queryPageResult(EmployeeQuery qo);

    List<Long> getRolesIdByEmployeeId(Long employeeId);

    List<String> getRoleSnByEmployeeId(Long employeeId);
}