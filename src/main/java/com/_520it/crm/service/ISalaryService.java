package com._520it.crm.service;

import com._520it.crm.domain.Salary;
import com._520it.crm.query.SalaryQuery;

import java.util.List;

/**
 * Created by fjz on 2017/8/26.
 */
public interface ISalaryService {
    int deleteByPrimaryKey(Long id);

    /**
     * return:受影响的行数
     * record:被保存的对象
     * @param salary
     * @return
     */
    int insert(Salary salary);

    /**
     * return:返回Salary对象
     * id:根据id取查询
     * @param id
     * @return
     */
    Salary selectByPrimaryKey(Long id);

    /**
     * return:返回Salary所有对象
     * @return
     */

    List<Salary> selectAll(SalaryQuery qo);

    /**
     *
     * record:被修改的对象
     * @param record
     * @return
     */
    int updateByPrimaryKey(Salary record);
}
