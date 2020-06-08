package com._520it.crm.mapper;

import com._520it.crm.domain.Test;
import com._520it.crm.query.TestQuery;

import java.util.List;

public interface TestMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Test record);

    Test selectByPrimaryKey(Long id);

    List<Test> selectAll();

    int updateByPrimaryKey(Test record);

    Long getTotal(TestQuery qo);

    List<Test> query(TestQuery qo);

    /*===================================*/
    List<Test> selectTestByName(Long id);

    List<Test> selectByResult(Long id);
    /*===================================*/
}