package com._520it.crm.mapper;

import com._520it.crm.domain.Receipt;
import com._520it.crm.domain.Upgrade;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface ReceiptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Receipt record);

    Receipt selectByPrimaryKey(Long id);

    List<Receipt> selectAll();

    int updateByPrimaryKey(Receipt record);

    Long queryPageCount(QueryObject qo);

    List<Upgrade> queryPageResult(QueryObject qo);

}