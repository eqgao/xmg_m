package com._520it.crm.mapper;

import com._520it.crm.domain.Upgrade;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface UpgradeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Upgrade record);

    Upgrade selectByPrimaryKey(Long id);

    List<Upgrade> selectAll();

    int updateByPrimaryKey(Upgrade record);

    Long queryPageCount(QueryObject qo);

    List<Upgrade> queryPageResult(QueryObject qo);

}