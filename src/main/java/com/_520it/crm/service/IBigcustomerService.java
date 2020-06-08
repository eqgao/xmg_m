package com._520it.crm.service;

import com._520it.crm.domain.Bigcustomer;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.BigcustomerQuery;

import java.util.List;

public interface IBigcustomerService {
	int deleteByPrimaryKey(Long id);

	int insert(Bigcustomer record);

	Bigcustomer selectByPrimaryKey(Long id);

	List<Bigcustomer> selectAll();

	int updateByPrimaryKey(Bigcustomer record);

	//查询分页结果
	PageResult queryPageResult(BigcustomerQuery qo);
}
