package com._520it.crm.service;

import com._520it.crm.domain.Address;
import com._520it.crm.domain.StudentInfo;

import java.util.List;

public interface IStudentInfoService {
    int deleteByPrimaryKey(Long id);

    int insert(StudentInfo record, Address address);

    StudentInfo selectByPrimaryKey(Long id);

    List<StudentInfo> selectAll();

    int updateByPrimaryKey(StudentInfo record);


}
