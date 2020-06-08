package com._520it.crm.mapper;

import com._520it.crm.domain.Goodauditor;
import java.util.List;

public interface GoodauditorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Goodauditor record);

    Goodauditor selectByPrimaryKey(Long id);

    List<Goodauditor> selectAll();

    int updateByPrimaryKey(Goodauditor record);
}