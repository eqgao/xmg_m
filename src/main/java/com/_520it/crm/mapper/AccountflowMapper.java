package com._520it.crm.mapper;

import com._520it.crm.domain.Accountflow;
import java.util.List;

public interface AccountflowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Accountflow record);

    Accountflow selectByPrimaryKey(Long id);

    List<Accountflow> selectAll();

    int updateByPrimaryKey(Accountflow record);
}