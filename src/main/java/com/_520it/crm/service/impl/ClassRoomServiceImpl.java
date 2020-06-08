package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.ClassRoom;
import com._520it.crm.mapper.ClassRoomMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ClassRoomQuery;
import com._520it.crm.service.IClassRoomService;

@Service
public class ClassRoomServiceImpl implements IClassRoomService {
	@Autowired
	private ClassRoomMapper mapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ClassRoom record) {
		return mapper.insert(record);
	}

	@Override
	public ClassRoom selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ClassRoom> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(ClassRoom record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPageResult(ClassRoomQuery qo) {
		//查询总结果数
		Long count = mapper.queryPageCount(qo);
		if (count == 0) {
			return new PageResult(count, Collections.EMPTY_LIST);
		}

		//查询结果集
		return new PageResult(count, mapper.queryPageResult(qo));
	}


}
