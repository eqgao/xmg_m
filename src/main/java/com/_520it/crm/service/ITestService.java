package com._520it.crm.service;

import com._520it.crm.domain.Test;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.TestQuery;

import java.util.List;

public interface ITestService {
    int deleteByPrimaryKey(Long id);

    int insert(Test record);

    Test selectByPrimaryKey(Long id);

    List<Test> selectAll();

    int updateByPrimaryKey(Test record);

    PageResult query(TestQuery qo);

    /*========================*/
    List<Test> selectTestByName(Long id);

    List<Test> selectByResult(Long id);
    /*========================*/
}
