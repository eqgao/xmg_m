package com._520it.crm.service;

import com._520it.crm.domain.Upgrade;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

/**
 * Created by hasee on 2017/8/26.
 */
public interface IUpgradeService {

    int deleteByPrimaryKey(Long id);

    int insert(Upgrade record);

    Upgrade selectByPrimaryKey(Long id);

    List<Upgrade> selectAll();

    int updateByPrimaryKey(Upgrade record);


    PageResult queryPageResult(QueryObject qo);
}
