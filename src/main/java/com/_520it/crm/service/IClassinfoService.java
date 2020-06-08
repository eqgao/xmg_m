package com._520it.crm.service;

import com._520it.crm.domain.Classinfo;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ClassinfoQuery;

import java.util.List;

public interface IClassinfoService {

	int deleteByPrimaryKey(Long id);

	int insert(Classinfo record);

	Classinfo selectByPrimaryKey(Long id);

	List<Classinfo> selectAll();

	int updateByPrimaryKey(Classinfo record);

	PageResult queryPageResult(ClassinfoQuery qo);

}
