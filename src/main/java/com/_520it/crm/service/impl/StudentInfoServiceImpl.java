package com._520it.crm.service.impl;

import com._520it.crm.domain.Address;
import com._520it.crm.domain.StudentInfo;
import com._520it.crm.mapper.StudentInfoMapper;
import com._520it.crm.service.IStudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentInfoServiceImpl implements IStudentInfoService {
    @Autowired
    private StudentInfoMapper mapper;

    @Override
    public int deleteByPrimaryKey(Long id) {

        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(StudentInfo record, Address address) {
        System.out.println(record);
        int i = mapper.insert(record);
        return i;

    }

    @Override
    public StudentInfo selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<StudentInfo> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(StudentInfo record) {

        return mapper.updateByPrimaryKey(record);
    }


}
