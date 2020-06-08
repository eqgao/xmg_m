package com._520it.crm.service.impl;

import com._520it.crm.domain.Upgrade;
import com._520it.crm.mapper.UpgradeMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IUpgradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by hasee on 2017/8/26.
 */
@Service
public class UpgradeServiceImpl implements IUpgradeService {
    @Autowired
    private UpgradeMapper mapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Upgrade record) {
        return mapper.insert(record);
    }

    @Override
    public Upgrade selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Upgrade> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Upgrade record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryPageResult(QueryObject qo) {
        //查询总结果数
        Long count = mapper.queryPageCount(qo);
        if (count == 0) {
            return new PageResult(count, Collections.EMPTY_LIST);
        }

        return new PageResult(count, mapper.queryPageResult(qo));
    }
}
