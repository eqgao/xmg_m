package com._520it.crm.mapper;

import com._520it.crm.domain.Expend;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface ExpendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Expend record);

    Expend selectByPrimaryKey(Long id);

    List<Expend> selectAll();

    int updateByPrimaryKey(Expend record);

    void auditor(Expend record);

    Long queryPageCount(QueryObject qo);

    List<Expend> queryPageResult(QueryObject qo);


}