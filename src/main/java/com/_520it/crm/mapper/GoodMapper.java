package com._520it.crm.mapper;

import com._520it.crm.domain.Good;
import java.util.List;

public interface GoodMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Good record);

    Good selectByPrimaryKey(Long id);

    List<Good> selectAll();

    int updateByPrimaryKey(Good record);
}