package com._520it.crm.mapper;

import com._520it.crm.domain.Checkon;
import com._520it.crm.query.CheckonQuery;

import java.util.List;

public interface CheckonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Checkon record);

    Checkon selectByPrimaryKey(Long id);

    List<Checkon> selectAll(CheckonQuery qo);

    int updateByPrimaryKey(Checkon record);

    Checkon get(Checkon checkon);
}