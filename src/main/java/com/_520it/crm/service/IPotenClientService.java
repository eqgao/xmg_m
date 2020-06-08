package com._520it.crm.service;

import java.util.List;

import com._520it.crm.domain.PotenClient;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

public interface IPotenClientService {
	int deleteByPrimaryKey(Long id);

	int insert(PotenClient record);

	PotenClient selectByPrimaryKey(Long id);

	List<PotenClient> selectAll();

	int updateByPrimaryKey(PotenClient record);

	PageResult query(QueryObject qo);


}
