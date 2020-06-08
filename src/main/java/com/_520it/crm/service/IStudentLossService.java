package com._520it.crm.service;

import java.util.List;

import com._520it.crm.domain.StudentLoss;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.StudentLossQuery;

public interface IStudentLossService {
	int deleteByPrimaryKey(Long id);

	int insert(Long id,String cause);

	StudentLoss selectByPrimaryKey(Long id);

	List<StudentLoss> selectAll();

	int updateByPrimaryKey(StudentLoss record);
	
	PageResult queryPageResult(StudentLossQuery qo);



}
