package com._520it.crm.service.impl;

import com._520it.crm.domain.Classinfo;
import com._520it.crm.mapper.ClassinfoMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ClassinfoQuery;
import com._520it.crm.service.IClassinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClassinfoServiceImpl implements IClassinfoService {
	@Autowired
	private ClassinfoMapper mapper;


	@Override
	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Classinfo record) {
        //保存班级
        int count = mapper.insert(record);
        return count;
	}

	@Override
	public Classinfo selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Classinfo> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Classinfo record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPageResult(ClassinfoQuery qo) {
		//查询总结果数
		Long count = mapper.queryPageCount(qo);
		if (count == 0) {
			return new PageResult(count, Collections.EMPTY_LIST);
		}

		//查询结果集
		return new PageResult(count, mapper.queryPageResult(qo));
	}
}
