package com._520it.crm.service.impl;

import com._520it.crm.domain.Dictionary;
import com._520it.crm.mapper.DictionaryMapper;
import com._520it.crm.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl implements IDictionaryService {
    @Autowired
    private DictionaryMapper mapper;

    @Override
    public int deleteByPrimaryKey(Long id) {

        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Dictionary record) {

        int count = mapper.insert(record);
        return count;
    }

    @Override
    public Dictionary selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Dictionary> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Dictionary record) {
        return mapper.updateByPrimaryKey(record);
    }


}
