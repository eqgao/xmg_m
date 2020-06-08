package com._520it.crm.service;

import com._520it.crm.domain.Receipt;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

/**
 * Created by hasee on 2017/8/27.
 */
public interface IReceiptService {
    int deleteByPrimaryKey(Long id);

    int insert(Receipt record);

    Receipt selectByPrimaryKey(Long id);

    List<Receipt> selectAll();

    int updateByPrimaryKey(Receipt record);

    PageResult queryPageResult(QueryObject qo);

    void auditor(Receipt receipt);
}
