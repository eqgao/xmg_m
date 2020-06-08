package com._520it.crm.service;

import com._520it.crm.domain.Expend;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

/**
 * Created by hasee on 2017/8/26.
 */
public interface IExpendService {

    int deleteByPrimaryKey(Long id);

    void auditor(Expend record);

    int insert(Expend record);

    Expend selectByPrimaryKey(Long id);

    List<Expend> selectAll();

    int updateByPrimaryKey(Expend record);

    PageResult queryPageResult(QueryObject qo);
}
