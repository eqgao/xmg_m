package com._520it.crm.mapper;

import com._520it.crm.domain.Jobinfo;
import com._520it.crm.query.QueryObject;

import java.util.List;
import java.util.Map;

public interface JobinfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Jobinfo record);

    Jobinfo selectByPrimaryKey(Long id);

    List<Jobinfo> selectAll();

    int updateByPrimaryKey(Jobinfo record);

    Long queryPageCount(QueryObject qo);

    List<Jobinfo> queryPageResult(QueryObject qo);

    List<Map<String, Object>> querySelectChart(QueryObject qo);

}