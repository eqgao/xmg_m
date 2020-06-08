package com._520it.crm.service;

import com._520it.crm.domain.Jobinfo;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;
import java.util.Map;

/**
 * Created by hasee on 2017/8/26.
 */
public interface IJobinfoService {

    int deleteByPrimaryKey(Long id);

    int insert(Jobinfo record);

    Jobinfo selectByPrimaryKey(Long id);

    List<Jobinfo> selectAll();

    int updateByPrimaryKey(Jobinfo record);

    PageResult queryPageResult(QueryObject qo);

    List<Map<String, Object>> querySelectChart(QueryObject qo);
}
