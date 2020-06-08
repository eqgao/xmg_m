package com._520it.crm.mapper;

import com._520it.crm.domain.Menu;
import com._520it.crm.query.MenuQuery;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    List<Menu> getRootMenu();

    List<Menu> select();

    Long queryPageCount(MenuQuery qo);

    List<?> queryPageResult(MenuQuery qo);

}