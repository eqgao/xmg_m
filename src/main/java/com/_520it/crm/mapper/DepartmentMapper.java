package com._520it.crm.mapper;

import com._520it.crm.domain.Department;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

	List<Department> selectList();

	Department selectDepartmentBySn(@Param("sn")String sn,@Param("name") String name);

	void changeDept(Long id);

	List<Department> selectChildDept(Long id);

}