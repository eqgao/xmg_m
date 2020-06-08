package com._520it.crm.service;

import com._520it.crm.domain.Dictionary;

import java.util.List;

public interface IDictionaryService {
    int deleteByPrimaryKey(Long id);

    int insert(Dictionary record);

    Dictionary selectByPrimaryKey(Long id);

    List<Dictionary> selectAll();

    int updateByPrimaryKey(Dictionary record);

}
