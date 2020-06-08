package com._520it.crm.mapper;

import com._520it.crm.domain.ClassRoom;
import com._520it.crm.query.ClassRoomQuery;

import java.util.List;

public interface ClassRoomMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassRoom record);

    ClassRoom selectByPrimaryKey(Long id);

    List<ClassRoom> selectAll();

    int updateByPrimaryKey(ClassRoom record);

    //总数据数
    Long queryPageCount(ClassRoomQuery qo);
    //结果集
    List<ClassRoom> queryPageResult(ClassRoomQuery qo);

}