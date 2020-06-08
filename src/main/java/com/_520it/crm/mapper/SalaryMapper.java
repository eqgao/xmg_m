package com._520it.crm.mapper;

import com._520it.crm.domain.Salary;
import com._520it.crm.query.SalaryQuery;

import java.util.List;

public interface SalaryMapper {
    /**
     * id:根据id取删除一列数据
     * return:受影响的行数
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * return:受影响的行数
     * record:被保存的对象
     * @param record
     * @return
     */
    int insert(Salary record);

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