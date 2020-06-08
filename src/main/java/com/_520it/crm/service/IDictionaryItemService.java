package com._520it.crm.service;

import com._520it.crm.domain.DictionaryItem;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface IDictionaryItemService {
    int deleteByPrimaryKey(Long id);

    int insert(DictionaryItem record);

    DictionaryItem selectByPrimaryKey(Long id);

    List<DictionaryItem> selectAll();

    int updateByPrimaryKey(DictionaryItem record);

    //查询分页结果
    PageResult queryPageResult(QueryObject qo);

    List<DictionaryItem> selectLevel();

    List<DictionaryItem> selectSource();

    List<DictionaryItem> selectStatus();

    List<DictionaryItem> selectAllStatus();


}
