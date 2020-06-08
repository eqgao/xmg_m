package com._520it.crm.service;

import com._520it.crm.domain.ClassRoom;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ClassRoomQuery;

import java.util.List;

public interface IClassRoomService {
	int deleteByPrimaryKey(Long id);

	int insert(ClassRoom record);

	ClassRoom selectByPrimaryKey(Long id);

	List<ClassRoom> selectAll();

	int updateByPrimaryKey(ClassRoom record);

	PageResult queryPageResult(ClassRoomQuery qo);

}
