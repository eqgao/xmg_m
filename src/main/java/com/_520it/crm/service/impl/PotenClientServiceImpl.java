package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.PotenClient;
import com._520it.crm.mapper.PotenClientMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IPotenClientService;

@Service
public class PotenClientServiceImpl implements IPotenClientService {
	@Autowired
	private PotenClientMapper mapper;

	@Override
	public int deleteByPrimaryKey(Long id) {

		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(PotenClient record) {

		return mapper.insert(record);

	}

	@Override
	public PotenClient selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<PotenClient> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(PotenClient record) {

		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult query(QueryObject qo) {
		Long total = mapper.getTotal(qo);
		if (total ==0 ) {
			return new PageResult(total, Collections.EMPTY_LIST);
		}
		List<PotenClient> rows = mapper.query(qo);
		
		return new PageResult(total, rows);
	}



}
