package com._520it.crm.mapper;

import com._520it.crm.domain.StudentInfo;
import java.util.List;

public interface StudentInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudentInfo record);

    StudentInfo selectByPrimaryKey(Long id);

    List<StudentInfo> selectAll();

    int updateByPrimaryKey(StudentInfo record);
}