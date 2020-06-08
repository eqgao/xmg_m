package com._520it.crm.mapper;

import com._520it.crm.domain.Account;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Account record);

    int insertBatch(@Param("list") List<Account> list);

    int updateBatch(@Param("list") List<Account> list);

    Account selectByPrimaryKey(Long id);

    List<Account> selectAll();

    int updateByPrimaryKey(Account record);

    int addAccount(BigDecimal money);

    int subtractAccount(BigDecimal money);

    Account select();

}