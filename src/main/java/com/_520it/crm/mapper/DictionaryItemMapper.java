package com._520it.crm.mapper;

import com._520it.crm.domain.DictionaryItem;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface DictionaryItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DictionaryItem record);

    DictionaryItem selectByPrimaryKey(Long id);

    List<DictionaryItem> selectAll();

    int updateByPrimaryKey(DictionaryItem record);

    Long queryPageCount(QueryObject qo);

    List<DictionaryItem> queryPageResult(QueryObject qo);

    List<DictionaryItem> selectLevel();

    List<DictionaryItem> selectSource();

    List<DictionaryItem> selectStatus();

    List<DictionaryItem> selectAllStatus();

}