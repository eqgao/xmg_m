package com._520it.crm.service;

import com._520it.crm.domain.TransferHistory;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface ITransferHistoryService {
    int deleteByPrimaryKey(Long id);

    int insert(TransferHistory record);

    TransferHistory selectByPrimaryKey(Long id);

    List<TransferHistory> selectAll();

    int updateByPrimaryKey(TransferHistory record);

    //查询分页结果
    PageResult queryPageResult(QueryObject qo);


}
