package com._520it.crm.service.impl;

import com._520it.crm.domain.Jobinfo;
import com._520it.crm.mapper.JobinfoMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IJobinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by hasee on 2017/8/30.
 */
@Service
public class JobinfoServiceImpl implements IJobinfoService {
    @Autowired
    private JobinfoMapper mapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Jobinfo record) {
        return mapper.insert(record);
    }

    @Override
    public Jobinfo selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Jobinfo> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Jobinfo record) {
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

    @Override
    public List<Map<String, Object>> querySelectChart(QueryObject qo) {
        return mapper.querySelectChart(qo);
    }
}
