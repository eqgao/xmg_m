package com._520it.crm.service.impl;

import com._520it.crm.domain.TransferHistory;
import com._520it.crm.mapper.TransferHistoryMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.ITransferHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TransferHistoryServiceImpl implements ITransferHistoryService {
    @Autowired
    private TransferHistoryMapper mapper;

    @Override
    public int deleteByPrimaryKey(Long id) {

        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TransferHistory record) {

        int count = mapper.insert(record);
        return count;
    }

    @Override
    public TransferHistory selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TransferHistory> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(TransferHistory record) {
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


}
