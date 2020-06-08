package com._520it.crm.mapper;

import com._520it.crm.domain.Bigcustomer;
import com._520it.crm.query.BigcustomerQuery;

import java.util.List;

public interface BigcustomerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Bigcustomer record);

    Bigcustomer selectByPrimaryKey(Long id);

    List<Bigcustomer> selectAll();

    int updateByPrimaryKey(Bigcustomer record);

    //分页
    Long queryPageCount(BigcustomerQuery qo);

    List<Bigcustomer> queryPageResult(BigcustomerQuery qo);
}