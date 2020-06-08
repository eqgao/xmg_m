package com._520it.crm.service.impl;

import com._520it.crm.domain.DictionaryItem;
import com._520it.crm.mapper.DictionaryItemMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IDictionaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DictionaryItemServiceImpl implements IDictionaryItemService {
    @Autowired
    private DictionaryItemMapper mapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        if (id < 19) {
            throw new RuntimeException("官方数据请勿动,要玩自己添加再玩");
        }
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DictionaryItem record) {
        int count = mapper.insert(record);
        return count;
    }

    @Override
    public DictionaryItem selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<DictionaryItem> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(DictionaryItem record) {
        if (record.getId() < 19) {
            throw new RuntimeException("官方数据请勿动,要玩自己添加再玩");
        }
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryPageResult(QueryObject qo) {
        //查询总结果数
        Long count = mapper.queryPageCount(qo);
        if (count == 0) {
            return new PageResult(count, Collections.EMPTY_LIST);
        }

        //查询结果集
        return new PageResult(count, mapper.queryPageResult(qo));

    }

    //============================================
    @Override
    public List<DictionaryItem> selectLevel() {
        return mapper.selectLevel();
    }

    @Override
    public List<DictionaryItem> selectSource() {
        return mapper.selectSource();
    }

    @Override
    public List<DictionaryItem> selectStatus() {
        return mapper.selectStatus();
    }
    //============================================
    /*==============================================================================*/
    @Override
    public List<DictionaryItem> selectAllStatus() {
        return mapper.selectAllStatus();
    }
    /*==============================================================================*/

}
