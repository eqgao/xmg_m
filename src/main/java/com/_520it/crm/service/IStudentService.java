package com._520it.crm.service;

import com._520it.crm.domain.Jobinfo;
import com._520it.crm.domain.PotenStudent;
import com._520it.crm.domain.Receipt;
import com._520it.crm.domain.Student;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.StudentQuery;

import java.util.List;

public interface IStudentService {
    int deleteByPrimaryKey(Long id);

    int insert(PotenStudent record, Student student);

    Student selectByPrimaryKey(Long id);

    Student selectBySid(Long Sid);

    List<Student> selectAll();

    int updateByPrimaryKey(Student record);

    PageResult queryPageResult(StudentQuery qo);
  
    void insertfees(Receipt receipt,Long param);

	int insert(Student record);

	void graduation(Jobinfo job,Long id);

	void changeGraduiteStatus(Long id);
   }

