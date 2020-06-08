package com._520it.crm.mapper;

import java.util.List;

import com._520it.crm.domain.StudentLoss;
import com._520it.crm.query.StudentLossQuery;

public interface StudentLossMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudentLoss record);

    StudentLoss selectByPrimaryKey(Long id);

    List<StudentLoss> selectAll();

    int updateByPrimaryKey(StudentLoss record);

    Long queryPageCount(StudentLossQuery qo);

    List<StudentLoss> queryPageResult(StudentLossQuery qo);

}