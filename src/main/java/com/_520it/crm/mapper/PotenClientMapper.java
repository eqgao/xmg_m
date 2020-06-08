package com._520it.crm.mapper;

import com._520it.crm.domain.PotenClient;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface PotenClientMapper {
	int deleteByPrimaryKey(Long id);

	int insert(PotenClient record);

	PotenClient selectByPrimaryKey(Long id);

	List<PotenClient> selectAll();

	int updateByPrimaryKey(PotenClient record);

	Long getTotal(QueryObject qo);

	List<PotenClient> query(QueryObject qo);
}