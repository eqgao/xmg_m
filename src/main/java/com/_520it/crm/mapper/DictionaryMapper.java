package com._520it.crm.mapper;

import com._520it.crm.domain.Dictionary;
import java.util.List;

public interface DictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Dictionary record);

    Dictionary selectByPrimaryKey(Long id);

    List<Dictionary> selectAll();

    int updateByPrimaryKey(Dictionary record);
}