package com._520it.crm.mapper;

import com._520it.crm.domain.ClassRoom;
import com._520it.crm.domain.Classinfo;
import com._520it.crm.query.ClassinfoQuery;

import java.util.List;

public interface ClassinfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Classinfo record);

    Classinfo selectByPrimaryKey(Long id);

    List<Classinfo> selectAll();

    int updateByPrimaryKey(Classinfo record);

    //总数据数
    Long queryPageCount(ClassinfoQuery qo);
    //结果集
    List<ClassRoom> queryPageResult(ClassinfoQuery qo);
}