package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Expend;
import com._520it.crm.mapper.ExpendMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IExpendService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by hasee on 2017/8/26.
 */
@Service
public class ExpendServiceImpl implements IExpendService {
    @Autowired
    private ExpendMapper mapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void auditor(Expend record) {
        Subject subject = SecurityUtils.getSubject();
        Employee emp = (Employee) subject.getPrincipal();
        record.setAuditor(emp);
        record.setAuditortime(new Date());
        record.setStatus(Expend.STATUS_HAS_AUDITOR);
        mapper.auditor(record);
    }

    @Override
    public int insert(Expend record) {
        return mapper.insert(record);
    }

    @Override
    public Expend selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Expend> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Expend record) {
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
