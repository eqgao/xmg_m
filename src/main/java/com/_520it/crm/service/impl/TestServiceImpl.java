package com._520it.crm.service.impl;

import com._520it.crm.domain.Test;
import com._520it.crm.mapper.TestMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.TestQuery;
import com._520it.crm.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TestServiceImpl implements ITestService {
    @Autowired
    private TestMapper mapper;

    @Override
    public int deleteByPrimaryKey(Long id) {

        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Test record) {
    	return mapper.insert(record);
    	
    }

    @Override
    public Test selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Test> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Test record) {

        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(TestQuery qo) {
        Long total = mapper.getTotal(qo);
        if (total == 0) {
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        List<Test> rows = mapper.query(qo);

        return new PageResult(total, rows);
    }

    /*===========================================*/
    @Override
    public List<Test> selectTestByName(Long id) {
        return mapper.selectTestByName(id);
    }
    /*===========================================*/

	@Override
	public List<Test> selectByResult(Long id) {
		return mapper.selectByResult(id);
	}

}
