package com._520it.crm.mapper;

import com._520it.crm.domain.TransferHistory;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface TransferHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TransferHistory record);

    TransferHistory selectByPrimaryKey(Long id);

    List<TransferHistory> selectAll();

    int updateByPrimaryKey(TransferHistory record);

    Long queryPageCount(QueryObject qo);

    List<TransferHistory> queryPageResult(QueryObject qo);
}