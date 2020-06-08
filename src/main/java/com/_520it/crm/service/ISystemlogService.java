package com._520it.crm.service;

import java.util.List;

import com._520it.crm.domain.Systemlog;

public interface ISystemlogService {
	int deleteByPrimaryKey(Long id);

	int insert(Systemlog record);

	Systemlog selectByPrimaryKey(Long id);

	List<Systemlog> selectAll();

	int updateByPrimaryKey(Systemlog record);

}
