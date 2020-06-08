package com._520it.crm.service;

import com._520it.crm.domain.Menu;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.MenuQuery;

import java.util.List;

public interface IMenuService {
	int deleteByPrimaryKey(Long id);

	int insert(Menu record);

	Menu selectByPrimaryKey(Long id);

	List<Menu> selectAll();

	List<Menu> select();

	int updateByPrimaryKey(Menu record);

	List<Menu> getRootMenu();

	PageResult queryPageResult(MenuQuery qo);

}
