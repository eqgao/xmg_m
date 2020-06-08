package com._520it.crm.service.impl;

import com._520it.crm.domain.Menu;
import com._520it.crm.mapper.MenuMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.MenuQuery;
import com._520it.crm.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService{
	@Autowired
	private MenuMapper mapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Menu record) {
		if(record.getParent().getId()==null){
			record.setUrl("");
		}
		return mapper.insert(record);
	}

	@Override
	public Menu selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Menu> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public List<Menu> select() {
		return mapper.select();
	}

	@Override
	public int updateByPrimaryKey(Menu record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Menu> getRootMenu() {
		return mapper.getRootMenu();
	}

	@Override
	public PageResult queryPageResult(MenuQuery qo) {
		//查询总结果数
		Long count = mapper.queryPageCount(qo);
		if (count == 0) {
			return new PageResult(count, Collections.EMPTY_LIST);
		}

		//查询结果集
		return new PageResult(count, mapper.queryPageResult(qo));
	}

}
