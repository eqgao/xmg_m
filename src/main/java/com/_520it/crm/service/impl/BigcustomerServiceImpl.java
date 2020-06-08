package com._520it.crm.service.impl;

import com._520it.crm.domain.Bigcustomer;
import com._520it.crm.mapper.BigcustomerMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.BigcustomerQuery;
import com._520it.crm.service.IBigcustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BigcustomerServiceImpl implements IBigcustomerService{
	@Autowired
	private BigcustomerMapper mapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Bigcustomer record) {
		return mapper.insert(record);
	}

	@Override
	public Bigcustomer selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Bigcustomer> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Bigcustomer record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPageResult(BigcustomerQuery qo) {

		//查询总结果数
		Long count = mapper.queryPageCount(qo);
		if (count == 0) {
			return new PageResult(count, Collections.EMPTY_LIST);
		}

		//查询结果集
		return new PageResult(count, mapper.queryPageResult(qo));
	}


}
